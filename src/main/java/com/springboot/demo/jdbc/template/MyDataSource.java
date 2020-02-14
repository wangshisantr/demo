package com.springboot.demo.jdbc.template;

import com.springboot.demo.jdbc.JdbcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @Description 自定义数据源
 * @Author wanglei
 * @Date 2020/2/14
 */
public class MyDataSource {
    private static Properties props = null;
    private String CLOSE = "close";

    static {
        InputStream in = MyDataSource.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            props = new Properties();
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 池中初始连接数（创建DataSource时池中就有5个连接）
    private static int initCount = 5;
    // 池中最小空闲连接数，小于这个数量就要创建连接并加入池中
    private static int minIdleCount = 3;
    // 池中最大允许存放的连接数
    private static int maxIdleCount = 10;
    // 当前池中连接数
    private static int currentIdleCount = 0;
    // 数据源创建连接的次数
    private static int createCount = 0;

    // LinkedList充当连接池，removeFirst取出连接，addLast归还连接
    private final static LinkedList<Connection> connectionsPool = new LinkedList<Connection>();

    /**
     * 空参构造，按照initCount预先创建一定数量的连接存入池中
     */
    public MyDataSource() {
        try {
            for (int i = 0; i < initCount; i++) {
                // 创建RealConnection
                Connection realConnection = DriverManager.getConnection(props.getProperty("url"),
                        props.getProperty("username"),
                        props.getProperty("password"));
                // 将RealConnection传入createProxyConnection()，得到代理连接并加入池中,currentIdleCount++
                connectionsPool.addLast(this.createProxyConnection(realConnection));
                currentIdleCount++;
            }
            System.out.println("-------连接池初始化结束，共初始化" + currentIdleCount + "个Connection-------");
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 公共方法，外界通过MyDataSource调用此方法得到代理连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        //同步代码
        synchronized (connectionsPool) {

            // 连接池中还有空闲连接，从池中取出，currentIdleCount--
            if (currentIdleCount > 0) {
                currentIdleCount--;
                if (currentIdleCount < minIdleCount) {
                    // 创建RealConnection
                    Connection realConnection = DriverManager.getConnection(props.getProperty("url"),
                            props.getProperty("username"),
                            props.getProperty("password"));
                    // 将RealConnection传入createProxyConnection()，得到代理连接并加入池中,currentIdleCount++
                    connectionsPool.addLast(this.createProxyConnection(realConnection));
                    currentIdleCount++;
                }
                return connectionsPool.removeFirst();
            }

            /*
             *  如果连接池没有空闲连接（都被用户拿走了），那么就再生成连接。比如第11个。
             *  不用考虑maxIdleCount，它指的是连接池最多存放多少个空闲连接，而不是数据源能生成多少个
             *  如果这第11个连接后期调用close，程序会判断当前连接池中的连接数是否大于maxIdleCount，如果已经存满了就直接销毁第11个连接，不会放入池中
             * */
            Connection realConnection = DriverManager.getConnection(props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));
            // 数据源创建连接后直接返回，没有加入池，也没有从池中取出，currentIdleCount不变
            return this.createProxyConnection(realConnection);
        }
    }

    /**
     * 私有方法，用于生成代理连接
     * 调用时机：数据源初始化，以及用户调用dataSource.getConnection时
     *
     * @param realConn
     * @return
     * @throws SQLException
     */
    private Connection createProxyConnection(Connection realConn) throws SQLException {
        // 这句代码仅仅是为了把realConn转为final，这样才能在匿名对象invocationHandler中使用
        final Connection realConnection = realConn;

        // 动态代理：返回Connection代理对象
        Connection proxyConnection = (Connection) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                realConnection.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 对close()方法进行拦截
                        if (CLOSE.equals(method.getName())) {
                            // 连接池空闲连接数小于最大空闲数，说明还能存得下，于是连接被归还到池中
                            if (MyDataSource.currentIdleCount < MyDataSource.maxIdleCount) {
                                MyDataSource.connectionsPool.addLast((Connection) proxy);
                                MyDataSource.currentIdleCount++;
                                // 返回1表示成功
                                return 1;
                            } else {
                                // 当前连接池满了，这个连接已经存不下，所以只能销毁（调用目标对象的close）
                                realConnection.close();
                                // 返回1表示成功
                                return 1;
                            }
                        }
                        return method.invoke(realConnection, args);
                    }
                });

        System.out.println("新建Connection(" + ++MyDataSource.createCount + ")：" + proxyConnection);
        return proxyConnection;
    }
}

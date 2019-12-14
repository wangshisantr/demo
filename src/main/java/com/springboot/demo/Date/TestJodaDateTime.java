package com.springboot.demo.date;


import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * 测试joda-dateTime
 *
 * @author wanglei
 * @date 2019/10/29
 */
public class TestJodaDateTime {
    public static void main(String[] args) {
        TestJodaDateTime t = new TestJodaDateTime();
        t.test1();
    }

    private void test1() {
        DateTime dt = new DateTime();
        dt.getYear();
        String string = dt.toString("yyyy-MM-dd HH:mm:ss");

        String s = UUID.randomUUID().toString().replace("-", "");
        System.out.println(dt);
        DateTime dt1 = DateTime.now();
        System.out.println(dt1);
        DateTime dt2 = new DateTime(new Date());
        System.out.println(dt2);
        DateTime dt6 = new DateTime(Calendar.getInstance());
        System.out.println(dt6);
        // 指定年月日点分秒生成(参数依次是：年,月,日,时,分,秒,毫秒)
        DateTime dt3 = new DateTime(2019, 5, 20, 13, 14, 0, 0);
        System.out.println(dt3);
        // 制定ISO8601生成
        DateTime dt4 = new DateTime("2014-08-01T12:32:3");
        System.out.println(dt4);
        DateTime dt5 = new DateTime("2014-08-01");
        System.out.println(dt5);

        //当前时间小时-->大一小时
        int year = dt.getYear();
        int month = dt.getMonthOfYear();
        int day = dt.getDayOfMonth();
        int hour = dt.getHourOfDay();
        DateTime dateTime = new DateTime(year, month, day, hour, 0, 0);
        DateTime dateTime2 = new DateTime(year, month, day, hour + 1, 0, 0);
        DateTime dateTime3 = dateTime.plusHours(1);
        System.out.println(dateTime.toDate());
        System.out.println(dateTime2.toDate());
        System.out.println(dateTime3.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE));
    }

}

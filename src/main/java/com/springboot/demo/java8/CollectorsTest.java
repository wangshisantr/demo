package com.springboot.demo.java8;

import com.springboot.domain.Student;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wanglei
 * @description
 * @date 2020/6/24
 */
public class CollectorsTest {
    public static void main(String[] args) {
        Student s1 = new Student(1L, "张三", 18);
        Student s2 = new Student(2L, "李四", 20);
        Student s3 = new Student(3L, "王五", 22);
        Student s4 = new Student(4L, "钱六", 16);
        Student s5 = new Student(5L, "田七", 53);

        //List
        ArrayList<Integer> collect = Stream.of(1, 1, 2, 3, 4, 5, 6, 8, 9, 0).distinct().sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        //Set
        HashSet<Integer> collect1 = Stream.of(11, 1, 4, 5, 1, 2, 2, 3, 6, 8, 9, 0)
                .collect(Collectors.toCollection(HashSet::new));
        //map
        Map<Long, Student> collect2 = Stream.of(s1, s2, s3, s4, s5)
                .collect(Collectors.toMap(Student::getId, Function.identity(),
                        (s6, s7) -> s6.getAge() > s7.getAge() ? s6 : s7));

        ConcurrentMap<Long, Student> collect3 = Stream.of(s1, s2, s3, s4, s5)
                .collect(Collectors.toConcurrentMap(Student::getId, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Student::getAge))));

        String join = StringUtils.join(Arrays.asList("s", "we"), ",");
        List<String> strings = Arrays.asList(join.split(","));

        // summaryStatistics
        Long collect4 = Stream.of(s1, s2, s3).map(Student::getId).collect(Collectors.counting());
        Optional<Long> max = Stream.of(s1, s2, s3).map(Student::getId).max(Long::compare);
        IntSummaryStatistics summaryStatistics = Stream.of(s1, s2, s3).collect(Collectors.summarizingInt(Student::getAge));
        List<Student> students = Arrays.asList(s1, s2, s3, s4);
        IntSummaryStatistics sum = students.stream().mapToInt(Student::getAge).summaryStatistics();
        System.out.println(sum);

        // filter
        List<Student> collect6 = Stream.of(s1, s2, s3, s4, s5).filter(student -> student.getAge() > 10).collect(Collectors.toList());

        //Map<String,Set<Integer>>
        //自定义map容器 和 下游收集器
        LinkedHashMap<String, Set<Integer>> collect5 = Stream.of(-6, -7, -8, -9, 1, 2, 3, 4, 5, 6)
                .filter(integer -> integer > -7)
                .sorted((i1, i2) -> Integer.compare(i1, i2))
                .collect(Collectors.groupingBy(integer -> {
                    if (integer < 0) {
                        return "小于";
                    } else if (integer == 0) {
                        return "等于";
                    } else {
                        return "大于";
                    }
                }, LinkedHashMap::new, Collectors.toSet()));

        System.out.println();
    }
}

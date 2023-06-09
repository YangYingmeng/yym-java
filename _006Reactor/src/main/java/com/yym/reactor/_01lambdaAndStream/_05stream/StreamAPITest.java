package com.yym.reactor._01lambdaAndStream._05stream;

import com.yym.reactor._01lambdaAndStream._01lambda.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
*   创建stream流的方法
*       1. 集合.stream()
*       2. Arrays.stream(数组)
*       3. Stream.of(1,2,3,4)
* */
public class StreamAPITest {
    public static void main(String[] args) {
        iterate();
    }

    // 过滤
    private static void filter() {
        Stream.of(1,2,3,4,5)
                .filter(num -> num%2==0)
                .forEach(System.out::println);
    }

    // 输出集合元素的数量 此处是输出3个
    private static void limit() {
        Stream.of(1,2,3,4,5)
                .limit(3)
                .forEach(System.out::println);
    }

    // 过滤前面的元素
    private static void skip() {
        Stream.of(1,2,3,4,5)
                .skip(3)
                .forEach(System.out::println);
    }

    // 去重
    private static void distinct() {
        Stream.of(1,2,3,3,5)
                .distinct()
                .forEach(System.out::println);
    }

    // 映射
    private static void map() {
        Stream.of("a", "b", "c")
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    // 映射+扁平化处理
    private static void flatMap() {
        Arrays.asList(1,2,3);
        ArrayList<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1,2));
        lists.add(Arrays.asList(3,4));
        System.out.println("flatMap: " + lists.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }

    // 排序
    private static void sort() {
        ArrayList<User> users = getUsers();
        users.stream().sorted(Comparator.comparingInt(User::getAge)).forEach(System.out::println);
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("张三", 18));
        users.add(new User("李四", 15));
        users.add(new User("王五", 20));
        return users;
    }

    // 匹配与查找
    private static void match() {
        System.out.println("所有员工年龄都小于21岁? "+getUsers().stream().allMatch(user -> user.getAge() < 21));
        System.out.println("存在员工年龄都小于16岁? "+getUsers().stream().anyMatch(user -> user.getAge() < 16));
        System.out.println("不存在员工年龄都小于16岁? "+getUsers().stream().anyMatch(user -> user.getAge() < 16));
        System.out.println("返回第一个员工信息: " + getUsers().stream().findFirst());
        System.out.println("返回当前流中的任意员工: " + getUsers().stream().findAny());
        System.out.println("总员工个数: " + getUsers().stream().count());
        System.out.println("年龄最大的员工信息: " + getUsers().stream().max(Comparator.comparingInt(User::getAge)));
        System.out.println("年龄最小的员工信息: " + getUsers().stream().min(Comparator.comparingInt(User::getAge)));
    }

    // 规约
    private static void reduce() {
        System.out.println("1-4的总和: " + Stream.of(1,2,3,4).reduce(Integer::sum).get());
    }

    // 收集
    private static void collect() {
        System.out.println(getUsers().stream().map(User::getName).collect(Collectors.toList()));
    }

    // 合并流
    private static void concat() {
        Stream<Integer> s1 = Stream.of(3, 2, 1).sorted(Comparator.comparingInt(num -> num));
        Stream<Integer> s2 = Stream.of(4,5,6).sorted(Comparator.comparingInt(num -> num));
        Stream<Integer> s3 = Stream.of(9,8,7).sorted(Comparator.comparingInt(num -> num));
        Stream.concat(s1, Stream.concat(s2, s3)).forEach(System.out::println);
    }

    // 对stream流加了断言处理, 当返回值为false时, 不在继续处理
    private static void takeWhile() {
        Stream.of("a", "b", "", "c").takeWhile(s -> !s.isEmpty()).forEach(System.out::println);
    }

    // 对stream流加了断言处理, 当返回值为true时, 不在继续处理
    private static void dropWhile() {
        Stream.of("a", "b", "", "c").dropWhile(s -> !s.isEmpty()).forEach(System.out::println);
    }

    // 设置一个初始种子值, 当断言条件为ture时执行后面foreach语句, 再执行x->x+3 循环
    private static void iterate() {
        IntStream.iterate(3, x -> x < 10, x -> x+ 3).forEach(System.out::println);
    }

}

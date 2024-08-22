package com.example.powerAdmin.test;

import com.example.powerAdmin.entity.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JDK1.8 Stream使用
 */
public class StreamMain {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User().setUserName("李靖宇").setEmail("lijingyu@163.com").setId(2L).setUserId(1));
        list.add(new User().setUserName("边雨暄").setEmail("bianyuxuan@163.com").setId(1L).setUserId(2));
        list.add(new User().setUserName("边雨暄").setEmail("bianyuxuan@163.com").setId(3L).setUserId(3));
        list.add(new User().setUserName("丁海欣").setEmail("dinghaixing@163.com").setId(5L).setUserId(4));
        list.add(new User().setUserName("杨泽").setEmail("yangze@163.com").setUserId(5));
        //保留符合条件的对象
        List<User> filter = list.stream().filter(e -> e.getUserName().contains("李")).collect(Collectors.toList());
        System.out.println("过滤之后集合为：" + filter);

        //去重 Comparator.comparing方法来指定比较器
        /**
         *       collectingAndThen方法则用于将上一步收集到的TreeSet对象转换成ArrayList类型的集合。
         *       它接受两个参数，第一个参数是一个Collector对象，用于将Stream中的元素收集到指定类型的集合中；
         *       第二个参数是一个Function对象，用于将第一个Collector返回的结果再次转换成另一种类型的对象。
         */
        List<User> distinctList = list.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getUserName))), ArrayList::new));
        List<User> distinctListMain = list.stream().distinct().collect(Collectors.toList());
        System.out.println("去重之后集合1为： " + distinctList);
        System.out.println("去重之后集合2为： distinctListMain = " + distinctListMain);

        //转map 合并函数
        Map<String, User> map = list.stream().collect(Collectors.toMap(User::getUserName, Function.identity(),(existing, replacement) -> existing));
        System.out.println("map = " + map);
        List<String> userNameList = list.stream().map(User::getUserName).collect(Collectors.toList());
        System.out.println("userNameList = " + userNameList);

        //peek消费操作：如果想对数据进行某些操作：读取，修改等
        List<User> peek = list.stream().peek(item -> item.setEmail(item.getUserName() + item.getEmail())).collect(Collectors.toList());
        System.out.println("peek消费操作 : = " + peek);
        //截取操作
        List<User> limit = list.stream().limit(2).collect(Collectors.toList());
        System.out.println("截取操作： = " + limit);

        //skip 跳过前N个元素 ，返回之后的元素
        List<User> skip = list.stream().skip(1).collect(Collectors.toList());
        System.out.println("skip = " + skip);

        //sort
        // 单字排序，根据ID升序（不做空字段处理，如果字段值为空会报错）
//        List<User> collect = list.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
//        System.out.println("单字段升序排列为 = " + collect);
        // 先按id升序排 再按userId升序排
//        List<User> collect1 = list.stream().sorted(Comparator.comparing(User::getId).thenComparing(User::getUserId)).collect(Collectors.toList());
//        System.out.println("多字段升序排列为 = " + collect1);
        //降序排列
//        List<User> collect2 = list.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
//        List<User> collect3 = list.stream().sorted(Comparator.comparing(User::getId, Comparator.reverseOrder())).collect(Collectors.toList());
//        System.out.println("降序排列方式1为 = " + collect2);
//        System.out.println("降序排列方式2为 = " + collect3);
        //对集合中的空字段进行处理，有空字段的元素放在集合开始/末尾 Comparator.nullsFirst 此方法接受单个参数比较器，该比较器是用于非空值的比较器
        List<User> collect = list.stream().sorted(Comparator.comparing(User::getId, Comparator.nullsFirst(Long::compareTo))).collect(Collectors.toList());
        System.out.println("collect = " + collect);

//        Collections.sort(list,Comparator.nullsFirst(Comparator.comparing(User::getId)));
//        Collections.sort(list, Comparator.nullsFirst(Comparator.comparing(User::getId)));
        System.out.println("list = " + list);
    }
}

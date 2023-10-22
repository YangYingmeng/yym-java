package com.yym.basic._02fanxing;

/**
 * @Description: 泛型方法, java的泛型是伪泛型, 编译时会进行泛型擦除, 泛型转为具体的类型
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-08-29 23:11
 */
public class FanXingDemo {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println("a + b = " + add(1,2.1));
        FanXingClass<String, Integer> aClass = new FanXingClass<>();
        aClass.setVar("test");
        aClass.setKey(1);
        System.out.println(aClass.getVar() + aClass.getKey());
        FanXingClass1 fanXingClass1 = new FanXingClass1();
        System.out.println(fanXingClass1.getVal());

        // 调用泛型方法
        FanXingClass<String, String> object = getObject(FanXingClass.class);
        object.setKey("test method");
        System.out.println(object.getKey());
    }

    // 使用泛型定义一个方法
    private static <T extends Number> double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // 泛型方法 <T> 声明该方法为泛型方法 T代表返回类型 Class<T> 泛型T的具体类型
    private static <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {
        T t = c.newInstance();
        return t;
    }
}

package com.yym.proxy._01lambda;

public class TestLambda {
    public static void main(String[] args) {
        // 1. 多态写法
        Factory factory = new SubClass();
        User user = (User)factory.getObject();
        System.out.println(user);

        // 2. 匿名内部类
         factory = new Factory() {
            @Override
            public Object getObject() {
                return new User("匿名内部类", 1);
            }
        };
        User user1 = (User) factory.getObject();
        System.out.println(user1);

        // 3. lambda表达式
        /*factory = () -> {
            return new User("lambda", 2);
        };*/
        // 表达式写法
        factory = () -> new User("lambda", 2);
        User user2 = (User) factory.getObject();
        System.out.println(user2);

        // 4. lambda 作为参数传递
        User user3 = getUserFormFactory(() -> {return new User("王五", 3);}, "User");
        System.out.println(user3);

        // 5. lambda作为函数的返回值
        User user4 = (User) getFactory().getObject();
        System.out.println(user4);
    }

        public static User getUserFormFactory(Factory factory, String beanName) {
            Object object = factory.getObject();
            if (object != null && object.getClass().getSimpleName().equals(beanName))
                return (User) object;
            return null;
        }

        public static Factory getFactory() {
            return () -> {
                return new User("赵六", 4);
            };
        }
}

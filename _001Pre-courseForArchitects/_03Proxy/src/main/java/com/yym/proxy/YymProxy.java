package com.yym.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 代理类
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-18 22:53
 */
public class YymProxy {
    // 设计一个类 记住代理类要代理的目标
    Person yym = new Yym();

    public Person getProxy() {
        return (Person) Proxy.newProxyInstance(this.getClass().getClassLoader(), yym.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /**
                 * InvocationHandler接口只定义了一个invoke方法，因此对于这样的接口，我们不用单独去定义一个类来实现该接口，
                 * 而是直接使用一个匿名内部类来实现该接口，new InvocationHandler() {}就是针对InvocationHandler接口的匿名实现类
                 */
                /**
                 * 在invoke方法编码指定返回的代理对象干的工作
                 * proxy : 把代理对象自己传递进来
                 * method：把代理对象当前调用的方法传递进来
                 * args:把方法参数传递进来
                 *
                 * 实际上执行的都是invoke方法里面的代码，
                 * 因此我们可以在invoke方法中使用method.getName()就可以知道当前调用的是代理对象的哪个方法
                 */
                if ("sing".equals(method.getName())) {
                    System.out.println("开始调用代理方法唱歌");
                    return method.invoke(yym, args);
                }
                if ("dance".equals(method.getName())) {
                    System.out.println("开始调用代理方法跳舞");
                    return method.invoke(yym, args);
                }
                return null;
            }
        });
    }

}

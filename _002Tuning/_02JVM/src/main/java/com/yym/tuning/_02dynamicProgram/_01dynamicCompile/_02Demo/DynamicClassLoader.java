package com.yym.tuning._02dynamicProgram._01dynamicCompile._02Demo;

/*
*   自定义类加载器
*   用于将编译好的class字节码加载
* */
public class DynamicClassLoader extends ClassLoader {

    // 存储编译好的java字节码
    private byte[] classBytes = null;
    // 加载java字节码
    public Class<?> dynLoadClass(String name, byte[] bytes) throws ClassNotFoundException {
        this.classBytes = bytes;
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return defineClass(name, this.classBytes, 0, this.classBytes.length);
    }
}


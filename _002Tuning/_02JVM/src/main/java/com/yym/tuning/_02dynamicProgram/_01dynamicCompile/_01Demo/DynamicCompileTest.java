package com.yym.tuning._02dynamicProgram._01dynamicCompile._01Demo;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/*
*   动态编译简单案例
*       1. JavaCompiler接口用于动态编译
*       2. ToolProvider类可以获取JavaCompiler接口的实例
*       3. 此方法动态编译会生成.class文件, 写入服务器的硬盘中 _02Demo进行优化处理
* */
public class DynamicCompileTest {

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        // 1.获取JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // 2.获取StandardJavaFileManager
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // 3.创建Java源文件
        File sourceFile = new File("HelloWorld.java");
        FileWriter writer = new FileWriter(sourceFile);
        writer.write("public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello World!\"); } }");
        writer.close();

        // 4.编译Java源文件
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
        boolean success = task.call();

        // 5.加载编译后的类
        if (success) {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new File(".").toURI().toURL()});
            Class<?> clazz = classLoader.loadClass("HelloWorld");
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, new Object[]{null});
        }

    }
}

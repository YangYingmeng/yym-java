package com.yym.tuning._02dynamicProgram._01dynamicCompile._02Demo;

import javax.tools.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/*
*   动态编译引擎
* */
public class DynamicCompileEngine {

    // 通过系统工具获取动态编译器
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    // 类加载器, 加载动态生成的类
    private final DynamicClassLoader dynamicClassLoader = new DynamicClassLoader();

    // 编译给定的Java代码
    public void complieJava(String className, String javaCode) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 收集诊断信息保存在一个集合中
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        // 建立用于保存被编译文件名的对象
        // 每个文件被保存在一个从JavaFileObject继承的类中
        ClassFileManager fileManager  = new ClassFileManager(compiler.getStandardFileManager(diagnostics, null, null));

        ArrayList<JavaFileObject> javaFileObjects = new ArrayList<>();
        javaFileObjects.add(new CharSequenceJavaFileObject(className, javaCode));

        // 使用编译选项可以改变默认编译行为。编译选项是一个元素为String类型的Iterable集合
        ArrayList<String> options = new ArrayList<>();
        options.add("-encoding");
        options.add("UTF-8");
        // 获取一个编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, javaFileObjects);

        // 编译源程序
        long start = System.currentTimeMillis();
        // 执行编译
        Boolean success = task.call();

        if (success) {
            // 如果编译成功, 用类加载器加载该类
            JavaClassObject javaClassObject = fileManager.getJavaClassObject();
            Class<?> clazz = dynamicClassLoader.dynLoadClass(className, javaClassObject.getBytes());
            long end = System.currentTimeMillis();
            System.out.println("编译消耗时间: " + (end - start));
            // java9 newInstance方法过时, 用如下方法替代
            Object instance = clazz.getDeclaredConstructor().newInstance();
            instance.toString();
        } else {
            //如果想得到具体的编译错误，可以对Diagnostics进行扫描
            String error = "";
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                error += compilePrint(diagnostic);
            }
        }
    }

    /**
     * 输出编译错误信息
     * @param diagnostic
     * @return String
     */
    private static String compilePrint(Diagnostic diagnostic) {
        System.out.println("Code:" + diagnostic.getCode());
        System.out.println("Kind:" + diagnostic.getKind());
        System.out.println("Position:" + diagnostic.getPosition());
        System.out.println("Start Position:" + diagnostic.getStartPosition());
        System.out.println("End Position:" + diagnostic.getEndPosition());
        System.out.println("Source:" + diagnostic.getSource());
        System.out.println("Message:" + diagnostic.getMessage(null));
        System.out.println("LineNumber:" + diagnostic.getLineNumber());
        System.out.println("ColumnNumber:" + diagnostic.getColumnNumber());
        return "Code:[" + diagnostic.getCode() + "]\n" +
                "Kind:[" + diagnostic.getKind() + "]\n" +
                "Position:[" + diagnostic.getPosition() + "]\n" +
                "Start Position:[" + diagnostic.getStartPosition() + "]\n" +
                "End Position:[" + diagnostic.getEndPosition() + "]\n" +
                "Source:[" + diagnostic.getSource() + "]\n" +
                "Message:[" + diagnostic.getMessage(null) + "]\n" +
                "LineNumber:[" + diagnostic.getLineNumber() + "]\n" +
                "ColumnNumber:[" + diagnostic.getColumnNumber() + "]\n";
    }

}

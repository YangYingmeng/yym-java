package com.yym.tuning._02dynamicProgram._01dynamicCompile._02Demo;

import javax.tools.*;
import java.io.IOException;

/*
*   类文件管理器
*   用于JavaCompiler将编译好后的class, 保存到JavaClassObject中
* */
public class ClassFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    // 保存编译后Class文件的对象
    private JavaClassObject javaClassObject = null;

    // 调用父类构造器
    @SuppressWarnings("unchecked")
    protected ClassFileManager(StandardJavaFileManager fileManager) {
        super(fileManager);
    }

    // 在task.call时执行, 将JavaFileObject对象的引用交给JavaCompiler, 让它将编译好后的class文件装载进来
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        if (javaClassObject == null) {
            javaClassObject = new JavaClassObject(className, kind);
        }
        return javaClassObject;
    }

    // 取得JavaFileObject对象
    public JavaClassObject getJavaClassObject() {
        return javaClassObject;
    }
}

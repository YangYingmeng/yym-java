package com.yym.tuning._02dynamicProgram._01dynamicCompile._02Demo;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

/*
*   保存Java源码
* */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {

    /*
    *   Java文件内容
    * */
    private String content = "";
    protected CharSequenceJavaFileObject(String className, String content) {
        // 创建一个字符串类型的URI, '///'表示空路径, 将 temp.com.Hello.class -> /temp/com/Hello.class + .java
        super(URI.create("string:///" + className.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.content = content;
    }

    // 实现getCharContent方法, 使得JavaCompiler可以从content获取java源码
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return content;
    }
}

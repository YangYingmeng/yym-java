package com.yym.tuning._02dynamicProgram._01dynamicCompile._02Demo;

import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String javaCode = "package temp.com; " + System.lineSeparator() +
                " public class Hello.class{" + System.lineSeparator() +
                " public String toString() {" + System.lineSeparator() +
                " 	System.out.println(\"HelloWorld111\");" + System.lineSeparator() +
                "	return \"hello world!\";" + System.lineSeparator() +
                " }" + System.lineSeparator() +
                " }";

        String javaCode1 = "package temp.com; " + System.lineSeparator() +
                " public class HelloTest {" + System.lineSeparator() +
                " public String toString() {" + System.lineSeparator() +
                " 	System.out.println(\"HelloWorld222\");" + System.lineSeparator() +
                "	return \"hello world!\";" + System.lineSeparator() +
                " }" + System.lineSeparator() +
                " }";

        String javaCode2 = "package temp.com; " + System.lineSeparator() +
                " public class HelloT {" + System.lineSeparator() +
                " public String toString() {" + System.lineSeparator() +
                " 	System.out.println(\"HelloWorld333\");" + System.lineSeparator() +
                "	return \"hello world!\";" + System.lineSeparator() +
                " }" + System.lineSeparator() +
                " }";

        DynamicCompileEngine dynamicCompileEngine = new DynamicCompileEngine();
        dynamicCompileEngine.complieJava("temp.com.Hello.class", javaCode);
        dynamicCompileEngine.complieJava("temp.com.HelloTest", javaCode1);
        dynamicCompileEngine.complieJava("temp.com.HelloT", javaCode2);
    }
}

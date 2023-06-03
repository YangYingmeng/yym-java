package com.yym.proxy;

/*
*   1. 注解概念
*        内部定义了配置参数 元数据
*        没有定义配置参数   标记
*   2. 注解的使用
*        1. 无参方法作为成员变量, 类型是String[] 名字为value, 又叫做配置参数
*        2. 如果只有一个配置参数, 名称为value, 赋值时value可以省略
*        3. 如果配置了配置参数, 在使用注解的时候必须赋值
*        4. 可以在配置的参数后面加 default 关键字, 为其赋默认值
*   3. 元注解
*        用于修饰其它注解的注解
*        Retention
*           用于指定修饰的那个注解的生命周期, 配置参数的类型是RetentionPolicy
*                   RetentionPolicy.SOURCE: 源文件中有效, 编译器会丢弃, .class文件中不保留注解信息
*                   RetentionPolicy.CLASS: CLASS文件中有效, 运行程序时不加载, 内存和JVM中不会保存, 默认状态
*                   RetentionPolicy.RUNTIME: 运行时有效, 保存在内存和JVM中, 可以通过反射获取
*       Target
*           用于指定注解可以修饰的对象类型, 属性 方法 构造器 类
*           ElementType.类型, 自己看枚举类
*       Documented
*           被该注解标记的注解 会被java文档提取
*       Inherited
*           被修饰的注解, 其子类将自动具有该注解,
*           例: ClassA 用AnnotationA修饰, ClassB extends ClassA, 用 @Inherited修饰ClassB ClassB也会有AnnotationA注解
* */
public @interface MyAnnotation {
    /*
    *   1.
    *   2. 如果只有一个配置参数, 名称为value, 赋值时value可以省略
    *   3. 如果配置了配置参数, 在使用注解的时候必须赋值
    * */
    String[] value();
}

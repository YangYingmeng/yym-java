package com.yym.tuning._01jvm._01classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @Description: 自定义类加载器, 并对文件加密
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-29 15:27
 */
public class _003YymClassLoaderWithEncription extends ClassLoader {

    private File file;
    public _003YymClassLoaderWithEncription(String sourcePath) {
        this.file = new File(sourcePath);
    }

    public _003YymClassLoaderWithEncription() {

    }
    public _003YymClassLoaderWithEncription(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes;
        try {
            classBytes = loadClassBytes(file);
            //defineClass方法可以把二进制流字节组成的文件转换为一个java.lang.Class
            return this.defineClass(name, classBytes, 0, classBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    // 将 .class文件转换为字节数组
    private byte[] loadClassBytes(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);
        while (true){
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        _003YymClassLoaderWithEncription yymClassLoader = new _003YymClassLoaderWithEncription("E:/Hello.class");
        Class<?> aClass = yymClassLoader.loadClass("Hello");
//        Class<?> aClass = Class.forName("Hello", true, yymClassLoader);
        Object hello = aClass.getDeclaredConstructor().newInstance();
        aClass.getMethod("hello").invoke(hello);
    }
}

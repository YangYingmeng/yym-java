package com.java.dp._03behavioralPatterns._02strategy;

// 想要排序, 先实现Comparable接口
// 泛型指定类型, 避免强转
public class Dog implements Comparable<Dog> {
    int food;

    public Dog(int food) {
        this.food = food;
    }


    @Override
    public int compareTo(Dog o) {
        // Dog c = (Dog) o;

        if (this.food < o.food)
            return -1;
        else if (this.food > o.food)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + food +
                '}';
    }

}

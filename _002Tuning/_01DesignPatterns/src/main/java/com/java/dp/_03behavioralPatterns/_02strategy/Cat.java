package com.java.dp._03behavioralPatterns._02strategy;

public class Cat implements Comparable<Cat> {
    int weight, height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }


    // 仍然不够灵活, 想要改成比较height需要删除该方法重新写
    @Override
    public int compareTo(Cat o) {
        // Cat c = (Cat) o;
        if (this.weight < o.weight)
            return -1;
        else if (this.weight > o.weight)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

}

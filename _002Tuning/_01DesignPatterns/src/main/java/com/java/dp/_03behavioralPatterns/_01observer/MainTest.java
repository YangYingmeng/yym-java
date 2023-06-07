package com.java.dp._03behavioralPatterns._01observer;

public class MainTest {
    public static void main(String[] args) {

        ConcreteSubject concreteSubject = new ConcreteSubject();

        concreteSubject.Attach(new ConcreteObserver(concreteSubject,"X"));
        concreteSubject.Attach(new ConcreteObserver(concreteSubject,"Y"));
        concreteSubject.Attach(new ConcreteObserver(concreteSubject,"Z"));

        concreteSubject.setSubjectState("ABC");
        concreteSubject.Notify();
    }
}

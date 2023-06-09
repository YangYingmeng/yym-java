package com.yym.reactor._02flow;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.SubmissionPublisher;

public class MyProcessor extends SubmissionPublisher<String> implements Processor<Integer, String> {
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {

    }

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(Integer integer) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

package com.java.dp._03behavioralPatterns._05mediator;

public class NormalPlayer extends AbstractPlayer{
    public NormalPlayer(Mediator mediator) {
        super(mediator);
        this.mediator.registerPlayer(this);
    }
}

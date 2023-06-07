package com.java.dp._03behavioralPatterns._05mediator;

public class VipPlayer extends AbstractPlayer{
    public VipPlayer(Mediator mediator) {
        super(mediator);
        this.mediator.registerPlayer(this);
    }
}

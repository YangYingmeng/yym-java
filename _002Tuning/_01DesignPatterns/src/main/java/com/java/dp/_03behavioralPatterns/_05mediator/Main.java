package com.java.dp._03behavioralPatterns._05mediator;

public class Main {
    public static void main(String[] args) {
        Mediator mediator=new Mediator();

        AbstractPlayer abstractPlayer1=new NormalPlayer(mediator);
        AbstractPlayer abstractPlayer2=new NormalPlayer(mediator);
        AbstractPlayer abstractPlayer3=new NormalPlayer(mediator);
        AbstractPlayer abstractPlayer4=new VipPlayer(mediator);
        //三号玩家胡牌
        abstractPlayer3.finsh();
        System.out.println(abstractPlayer1.getScore());//99
        System.out.println(abstractPlayer2.getScore());//99
        System.out.println(abstractPlayer3.getScore());//104
        System.out.println(abstractPlayer4.getScore());//99

    }
}


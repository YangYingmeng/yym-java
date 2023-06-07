package com.java.dp._03behavioralPatterns._05mediator;

import java.util.ArrayList;
import java.util.List;

//调停者
public class Mediator  {
    //建立容器收集对象
    private List<AbstractPlayer> players=new ArrayList<>();
    //将玩家注册
    public void registerPlayer(AbstractPlayer player){
        this.players.add(player);
    }



    public void finish(AbstractPlayer player) {
        for (AbstractPlayer abstractPlayer : players) {
            if(abstractPlayer==player){
                abstractPlayer.setFinished(true);
                if (abstractPlayer instanceof VipPlayer)
                    abstractPlayer.addPoints(6);
                else
                    abstractPlayer.addPoints(3);
            }else {
                abstractPlayer.setFinished(false);
                abstractPlayer.cutPoints(1);
            }
        }
    }
}

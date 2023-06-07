package com.java.dp._03behavioralPatterns._05mediator;

// 玩家抽象类
public abstract class AbstractPlayer {

    // 调停者持有
    protected Mediator mediator;
    // 分数
    private Integer score;
    // 是否胡牌
    private boolean finished;

    public void setFinished (boolean finished) {
        this.finished = finished;
    }

    // 构造方法
    public AbstractPlayer(Mediator mediator) {
        this.score = 100;
        this.mediator = mediator;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int point) {
        this.score += point;
    }

    public void cutPoints(int point) {
        this.score -= point;
    }

    // 胡牌
    public void finsh() {
        this.finished = true;
    }
}

package com.java.dp._03behavioralPatterns._04facade.facade;


import com.java.dp._03behavioralPatterns._04facade.module.Order;
import com.java.dp._03behavioralPatterns._04facade.module.Pay;
import com.java.dp._03behavioralPatterns._04facade.module.Refund;
import com.java.dp._03behavioralPatterns._04facade.module.Select;

// 门面者, 对外提供的方法, 具体的内部系统调用外部不用管
// 买东西: 挑选 -> 下单 -> 付款
// 退货:  挑选 -> 下单 -> 付款 -> 退款
public class Facade {
    // 对外开放 买东西功能
    public void buy() {
        Select select = Select.getSelect();
        select.select();
        Order order = Order.getOrder();
        order.order();
        new Pay().pay();
    }

    // 对外开放 退东西
    public void returnGoods() {
        Select select = Select.getSelect();
        select.select();
        Order order = Order.getOrder();
        order.order();
        new Refund().refund();
    }
}

package io.github.xausky.stream.controller;

import io.github.xausky.stream.entity.Order;
import io.github.xausky.stream.entity.OrderState;
import io.github.xausky.stream.event.DecrbyRequestEvent;
import io.github.xausky.stream.event.OrderStockChannel;
import io.github.xausky.stream.service.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xausky on 1/12/17.
 */
@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStockChannel orderStockChannel;

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public Iterable<Order> list(){
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    public Order post(@RequestBody Order order){
        order.setState(OrderState.START);
        order = orderRepository.save(order);
        DecrbyRequestEvent event = new DecrbyRequestEvent(order.getId(),order.getGoodsId(),order.getNumber());
        if(orderStockChannel.output().send(MessageBuilder.withPayload(event).build())){
            order.setState(OrderState.REQUEST);
        }else {
            order.setState(OrderState.FAIL);
        }
        order = orderRepository.save(order);
        return order;
    }
}

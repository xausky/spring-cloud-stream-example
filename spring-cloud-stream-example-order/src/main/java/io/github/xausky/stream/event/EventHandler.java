package io.github.xausky.stream.event;

import io.github.xausky.stream.entity.Order;
import io.github.xausky.stream.entity.OrderState;
import io.github.xausky.stream.service.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * Created by xausky on 1/12/17.
 */
@EnableBinding(OrderStockChannel.class)
public class EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);
    @Autowired
    private OrderRepository orderRepository;

    @StreamListener(OrderStockChannel.INPUT)
    public void process(DecrbyEvent event){
        logger.info("process:{}",event);
        if(event instanceof DecrbyFailEvent){
            process((DecrbyFailEvent)event);
        }else if(event instanceof DecrbySuccessEvent){
            process((DecrbySuccessEvent)event);
        }
    }

    public void process(DecrbyFailEvent event){
        Order order = orderRepository.findOne(event.getId());
        order.setState(OrderState.FAIL);
        orderRepository.save(order);
    }

    public void process(DecrbySuccessEvent event){
        Order order = orderRepository.findOne(event.getId());
        order.setState(OrderState.SUCCESS);
        orderRepository.save(order);
    }
}

package io.github.xausky.stream.event;

import io.github.xausky.stream.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by xausky on 1/12/17.
 */
@EnableBinding(OrderStockChannel.class)
public class EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);

    @Autowired
    private StockService stockService;

    @SendTo(OrderStockChannel.OUTPUT)
    @StreamListener(OrderStockChannel.INPUT)
    public DecrbyEvent process(DecrbyEvent event){
        logger.info("decrby request:{}", event);
        if(event instanceof DecrbyRequestEvent) {
            if (stockService.decrby(((DecrbyRequestEvent) event).getGoodsId(), ((DecrbyRequestEvent)event).getNumber())) {
                return new DecrbySuccessEvent(event.getId());
            } else {
                return new DecrbyFailEvent(event.getId());
            }
        }
        return null;
    }
}

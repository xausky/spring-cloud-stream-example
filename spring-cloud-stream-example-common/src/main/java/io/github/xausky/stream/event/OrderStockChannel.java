package io.github.xausky.stream.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by xausky on 1/12/17.
 */
public interface OrderStockChannel {
    String INPUT = "order-stock-channel-input";
    String OUTPUT = "order-stock-channel-output";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();
}

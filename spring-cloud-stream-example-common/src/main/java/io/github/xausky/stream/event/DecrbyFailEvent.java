package io.github.xausky.stream.event;

/**
 * Created by xausky on 1/12/17.
 */
public class DecrbyFailEvent extends DecrbyEvent {
    public DecrbyFailEvent(Long id){
        this.setId(id);
    }
}

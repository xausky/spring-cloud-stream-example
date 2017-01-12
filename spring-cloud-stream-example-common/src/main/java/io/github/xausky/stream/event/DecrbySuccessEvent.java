package io.github.xausky.stream.event;

/**
 * Created by xausky on 1/12/17.
 */
public class DecrbySuccessEvent extends DecrbyEvent {
    public DecrbySuccessEvent(Long id){
        this.setId(id);
    }
}

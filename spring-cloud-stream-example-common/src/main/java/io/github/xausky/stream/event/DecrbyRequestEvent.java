package io.github.xausky.stream.event;

/**
 * Created by xausky on 1/12/17.
 */
public class DecrbyRequestEvent extends DecrbyEvent {
    private Long goodsId;
    private Long number;

    public DecrbyRequestEvent(Long id, Long goodsId, Long number){
        this.setId(id);
        this.setGoodsId(goodsId);
        this.setNumber(number);
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}

package io.github.xausky.stream.controller;

import io.github.xausky.stream.entity.Stock;
import io.github.xausky.stream.event.DecrbyRequestEvent;
import io.github.xausky.stream.event.DecrbySuccessEvent;
import io.github.xausky.stream.event.OrderStockChannel;
import io.github.xausky.stream.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scala.util.parsing.combinator.testing.Str;

import java.util.List;

/**
 * Created by xausky on 1/12/17.
 */
@RestController
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private OrderStockChannel orderStockChannel;

    @RequestMapping(value = "/api/stocks", method = RequestMethod.GET)
    public List<Stock> list() {
        return stockService.list();
    }
}

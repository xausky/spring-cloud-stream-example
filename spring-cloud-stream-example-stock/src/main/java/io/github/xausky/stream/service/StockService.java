package io.github.xausky.stream.service;

import io.github.xausky.stream.entity.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by xausky on 1/12/17.
 */
@Service
public class StockService {
    private static final Logger logger = LoggerFactory.getLogger(StockService.class);
    @Autowired
    private JedisPool jedisPool;

    public List<Stock> list(){
        List<Stock> stocks = new LinkedList<>();
        Jedis jedis = jedisPool.getResource();
        try {
            Set<String> keys = jedis.keys("stock.*");
            for(String key:keys){
                Stock stock = new Stock();
                stock.setId(Long.valueOf(key.split("\\.")[1]));
                stock.setStock(Long.valueOf(jedis.get(key)));
                stocks.add(stock);
            }
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return stocks;
    }

    public boolean decrby(Long id, Long number){
        Jedis jedis = jedisPool.getResource();
        try {
            Long stock = jedis.decrBy("stock."+id,number);
            if(stock<0){
                jedis.incrBy("stock."+id,number);
                return false;
            }
            return true;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}

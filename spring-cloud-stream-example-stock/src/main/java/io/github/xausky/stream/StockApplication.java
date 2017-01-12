package io.github.xausky.stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import scala.util.parsing.combinator.testing.Str;

import java.net.URI;

/**
 * Created by xausky on 1/12/17.
 */
@SpringBootApplication
public class StockApplication {
    @Value("${spring.redis.host}")
    private String redisHost;

    public static void main(String[] args){
        SpringApplication.run(StockApplication.class,args);
    }

    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool(redisHost);

        // Init data
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set("stock.1","10");
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }

        return jedisPool;
    }
}

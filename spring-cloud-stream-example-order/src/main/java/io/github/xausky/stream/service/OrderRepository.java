package io.github.xausky.stream.service;

import io.github.xausky.stream.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by xausky on 1/12/17.
 */
@Service
public interface OrderRepository extends CrudRepository<Order,Long> {
}

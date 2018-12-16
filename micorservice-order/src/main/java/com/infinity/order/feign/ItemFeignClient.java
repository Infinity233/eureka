package com.infinity.order.feign;

import com.infinity.order.pojo.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("microservice-item")
public interface ItemFeignClient {

    @GetMapping("/item/{id}")
    Item queryBuItemId(@PathVariable("id") Long id);
}

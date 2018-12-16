package com.infinity.order.service;

import com.infinity.order.feign.ItemFeignClient;
import com.infinity.order.pojo.Item;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
//@DefaultProperties(defaultFallback = "defalut")
public class ItemService {

    @Autowired
    private RestTemplate rest;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ItemFeignClient itemFeignClient;

    @HystrixCommand(fallbackMethod = "abc", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public Item queryItemById(Long id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return itemFeignClient.queryBuItemId(id);
    }

//    @HystrixCommand(fallbackMethod = "abc")
//    public Item queryItemById(Long id) {
//        String serviceId = "microservice-item";
//
//        return rest.getForObject("http://" + serviceId + "/item/" + id, Item.class);
//    }

    public Item abc(Long id) {

        return Item.builder().id(id).title("error").build();
    }

//    public Item queryItemById(Long id) {
////        String url = "http://localhost:8081/item/" + id;
////        return rest.getForObject(url, Item.class);
//
//        String serviceId = "microservice-item";
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
//
//        if (instances.isEmpty()) {
//            return null;
//        }
//
//        instances.forEach(System.out::println);
//
//        ServiceInstance instance = instances.get(0);
//        String url = instance.getHost() + ":" + instance.getPort();
//
//        return rest.getForObject("http://" + url + "/item/" + id, Item.class);
//    }
}

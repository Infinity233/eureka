package com.infinity.order.service;

import com.infinity.order.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {

//    @Autowired
    private RestTemplate rest = new RestTemplate();

    @Autowired
    private DiscoveryClient discoveryClient;

    public Item queryItemById(Long id) {
        String serviceId = "microservice-item";

        return rest.getForObject("http://" + serviceId + "/item/" + id, Item.class);
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
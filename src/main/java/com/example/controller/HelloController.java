package com.example.controller;


import com.example.dto.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String index(){
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("/hello, host:" + instance.getHost()+",service_id:"+instance.getServiceId());
        return "Hello Word";
    }

    @RequestMapping(value = "/hellol", method= RequestMethod.GET)
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value = "/hello2", method= RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        return new User(name,age);
    }
    @RequestMapping(value = "/hello3", method= RequestMethod.POST)
    public String hello(@RequestBody User user) {
        return "Hello "+ user.getName() + ", " + user.getAge ();
    }



}

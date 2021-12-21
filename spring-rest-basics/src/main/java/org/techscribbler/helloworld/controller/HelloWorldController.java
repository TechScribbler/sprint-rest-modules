package org.techscribbler.helloworld.controller;

import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.techscribbler.helloworld.bean.HelloWorldBean;
import org.techscribbler.user.bean.User;
import org.techscribbler.user.service.UserDaoService;

@RestController
@Slf4j
public class HelloWorldController {
    /*@RequestMapping(method = RequestMethod.GET,path="/hello-world")
    public String sayHelloWorld(){
        return "Hello World";
    }*/
    @GetMapping(path="/hello-world")
    public String sayHelloWorld(){
        return "Hello World";
    }

    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean sayHelloWorldThroughBean(){
        return new HelloWorldBean("Hello World");
    }
    @GetMapping(path="/hello-world/{name}")
    public HelloWorldBean sayHelloWorldWithPathVaribale(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s ",name));
    }

}

package com.example.demo.controller;

import com.example.demo.socket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/index2")
    public String index2() {
        return "Hello World2!";
    }

    @Autowired
    private WebSocket webSocket;

    @GetMapping("/webs")
    public String webs() {
        webSocket.onMessage("msg!!!");
        return "Hello World2!";
    }

}

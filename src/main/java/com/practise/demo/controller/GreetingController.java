package com.practise.demo.controller;

import com.practise.demo.entity.Greeting;
import com.practise.demo.entity.Student;
import com.practise.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    StudentService service;

    @GetMapping("/greet")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greet/{id}")
    @ResponseBody
    public Greeting sayHelloId(@PathVariable Integer id) {
        Student s ;
        try {
            s = service.getById(id) ;
        } catch (NoSuchElementException e) {
            s = service.getById(1) ;
        }

        String name = s.getName() ;

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
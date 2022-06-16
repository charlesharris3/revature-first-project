package com.example.demo.config;

import com.example.demo.model.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean //Create a Bean for Item model
    public Item item(){
        return new Item();
    }
}

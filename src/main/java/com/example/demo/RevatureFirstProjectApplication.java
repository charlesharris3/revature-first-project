package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RevatureFirstProjectApplication {
	public static void main(String[] args) { SpringApplication.run(RevatureFirstProjectApplication.class, args);} //

	@RequestMapping("/home")
	public String greet(){ return "If you can read this, it fucking works...!"; }
}

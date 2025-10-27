package com.example.springwithgradle.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.springwithgradle")
public class AppConfig {
}

// You seen in the navin reddy video how to work with the Applicaiton Context.xml file.
//this is that file in java form with spring controlling all the implementaion
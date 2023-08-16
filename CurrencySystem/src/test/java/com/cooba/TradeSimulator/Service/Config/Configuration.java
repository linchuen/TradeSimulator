package com.cooba.TradeSimulator.Service.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;

@MapperScan("com.cooba.TradeSimulator.Mapper")
@ComponentScan("com.cooba.TradeSimulator.DataLayer")
@ActiveProfiles(value = "test")
@TestConfiguration
@EnableAspectJAutoProxy
public class Configuration {
    @Autowired(required = false) ApplicationContext context;

    public void checkBeans(){
        System.out.println("Loaded beans");
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }
}

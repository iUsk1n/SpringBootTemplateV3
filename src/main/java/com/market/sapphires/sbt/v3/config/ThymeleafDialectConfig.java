package com.market.sapphires.sbt.v3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.market.sapphires.sbt.v3.util.CustomTagDialect;

@Configuration
public class ThymeleafDialectConfig {

    @Bean
    public CustomTagDialect customTagDialect() {
        return new CustomTagDialect();
    }

}

package com.midnights.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.midnights.demo")
@EnableJpaRepositories(basePackages = "com.midnights.demo")
public class JpaConfiguration {


}

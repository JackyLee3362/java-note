package edu.note.spring.redis;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer configurationBuilderCustomizer(){
        return configBuilder -> configBuilder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}

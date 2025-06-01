package com.example.demo.config;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DatabaseConfig {
    @Bean
    CommandLineRunner initDatabase(PostRepository repository) {
        return args -> {
            repository.save(new Post(null, "superman@dc.com", 12345L, true, 1001L, "Man of Steel"));
            repository.save(new Post(null, "batman@dc.com", 67890L, true, 1002L, "Dark Knight"));
            repository.save(new Post(null, "wonderwoman@dc.com", 11111L, true, 1003L, "Amazon Warrior"));
        };
    }
}
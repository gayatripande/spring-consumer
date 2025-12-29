package com.example.springconsumer;

import com.example.springconsumer.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureStubRunner
@DirtiesContext
class ProductConsumerTest {

    @Test
    void shouldGetProductFromProducer() {
        RestTemplate restTemplate = new RestTemplate();

        // The stub is running on localhost:8080 because of the 'ids' config above
        Product product = restTemplate.getForObject("http://localhost:8080/products/101", Product.class);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(101L);
        assertThat(product.getName()).isEqualTo("Wireless Mouse");
    }
}
package kr.co.strato.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(nameGenerator = kr.co.strato.catalog.config.FullBeanNameGenerator.class)
@EntityScan()
@EnableJpaRepositories()
public class CmpCatalogApplication {
	public static void main(String[] args) {
		SpringApplication.run(CmpCatalogApplication.class, args);
	}

}

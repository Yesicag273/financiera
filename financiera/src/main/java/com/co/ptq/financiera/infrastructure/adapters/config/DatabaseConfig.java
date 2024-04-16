package com.co.ptq.financiera.infrastructure.adapters.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class DatabaseConfig {
	@Bean
	public DataSource dataSource() {
		// Configuración de la conexión a la base de datos (Oracle)
		// ...
		
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		// Configuración del Entity Manager para JPA (Hibernate)
		// ...
		return entityManagerFactoryBean;
	}
}
package com.teste.pratico;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

//@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableJpaRepositories("com.teste.pratico.repository")
public class TestePraticoApplication extends SpringBootServletInitializer {

	@Bean
	public TomcatServletWebServerFactory containerFactory() {
		return new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				final int cacheSize = 40 * 1024;
				StandardRoot standardRoot = new StandardRoot(context);
				standardRoot.setCacheMaxSize(cacheSize);
				context.setResources(standardRoot); // This is what made it work in my case.
				logger.info(String.format("New cache size (KB): %d", context.getResources().getCacheMaxSize()));
				System.out.println(String.format("New cache size (KB): %d", context.getResources().getCacheMaxSize()));
			}

			protected void customizeConnector(Connector connector) {
				int maxSize = 50000000;
				super.customizeConnector(connector);
				connector.setMaxPostSize(maxSize);
				connector.setMaxSavePostSize(maxSize);
				if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {

					((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(maxSize);
					logger.info("Set MaxSwallowSize " + maxSize);
				}
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TestePraticoApplication.class, args);
	}

}
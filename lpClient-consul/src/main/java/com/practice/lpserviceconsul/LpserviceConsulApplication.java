package com.practice.lpserviceconsul;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@EnableAutoConfiguration
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class LpserviceConsulApplication {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Value("${spring.lpservice.name:lpservice-demo}")
	private String appName;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(LpserviceConsulApplication.class, args);
	}
	
	@RequestMapping("/me-client")
	public ServiceInstance me() {
		return discoveryClient.getLocalServiceInstance();
	}
	
	@RequestMapping("/rest-client")
	public String rest() {
		System.out.println("Appname is "+appName);
		return this.restTemplate.getForObject("http://"+appName+"/me", String.class);
	}

	@RequestMapping("/rest-servclient")
        public String restclient() {
                System.out.println("Appname is "+appName);
                return "adding client response with -- "+this.restTemplate.getForObject("http://"+appName+"/hello", String.class);
        }
	
	@RequestMapping("hello-client")
	public String hello(){
		return "hello from client";
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

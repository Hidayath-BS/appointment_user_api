package org.zerhusen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class JwtDemoApplication {

	
	 @PostConstruct
	    public void init(){
	        TimeZone.setDefault(TimeZone.getTimeZone("IST"));   // It will set UTC timezone
	        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
	    }
	
    public static void main(String[] args) {
        SpringApplication.run(JwtDemoApplication.class, args);
    }
}

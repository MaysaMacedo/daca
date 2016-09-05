package bootwildfly;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que constroi a aplicação Spring Boot.
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class Application extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @RequestMapping("/user")
    public Principal user(Principal principal) {
      return principal;
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/**", "/index.html", "/login", "/", "/problem/**")
        .permitAll().anyRequest().authenticated()
        .and().csrf().disable()
//        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
//		// do not use any default headers unless explicitly listed
    	.headers()
		.defaultsDisabled()
		.cacheControl();
    }
}


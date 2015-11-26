package hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by david on 11/26/2015.
 */
@Configuration
@EnableWebSecurity
@ComponentScan
public class TestConfig {
}

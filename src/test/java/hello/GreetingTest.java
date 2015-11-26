package hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by david on 11/26/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
@ContextConfiguration(classes = TestConfig.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class GreetingTest {
    @Autowired
    private GreetingJpaRepository greetingJpaRepository;

    @Autowired
    private GreetingRepository greetingRepository;

    @Test
    public void TestGreeting()
    {
        Assert.assertEquals(3,this.greetingJpaRepository.findAll().size());
    }
}

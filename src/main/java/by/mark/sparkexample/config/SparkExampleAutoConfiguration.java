package by.mark.sparkexample.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        SparkDevConfiguration.class,
        SparkLocalConfiguration.class,
})
@EnableConfigurationProperties({
        SparkDevConfiguration.class
})
@Configuration
public class SparkExampleAutoConfiguration {
}

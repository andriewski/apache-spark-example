package by.mark.sparkexample.config;

import org.apache.spark.SparkConf;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile(AppProfile.LOCAL)
public class SparkLocalConfiguration {

    @Bean
    @ConditionalOnMissingBean(SparkConf.class)
    SparkConf sparkConf() {
        return new SparkConf()
                .setAppName("Simple Application")
                .setMaster("local[2]")
                .set("spark.executor.memory", "2g");
    }
}

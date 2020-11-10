package by.mark.sparkexample.config;

import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.spark.SparkConf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile(AppProfile.DEV)
@ConfigurationProperties("aws")
@Setter
public class SparkDevConfiguration {

    @Bean
    @SneakyThrows
    SparkConf sparkConf() {
        return new SparkConf()
                .setAppName("Simple Application")
                .setMaster("local")
                .set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
                .set("spark.executor.memory", "2g");
    }
}

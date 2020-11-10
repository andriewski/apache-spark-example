package by.mark.sparkexample;

import by.mark.sparkexample.config.AppProfile;
import by.mark.sparkexample.util.SparkUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles(AppProfile.DEV)
@SpringBootTest
public class SparkExecutionDevTests {

    @Autowired
    SparkConf sparkConf;

    @Test
    void shouldCountRowsFromAws() {
        //given:
        String bucketPath = "s3a://mrk.blabla/";
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> logData = sc.textFile(bucketPath).cache();

        //when:
        long numAs = SparkUtils.countLettersInRdd(logData, 'a');
        long numBs = SparkUtils.countLettersInRdd(logData, 'b');
        sc.close();

        //then:
        assertEquals(numAs, 24);
        assertEquals(numBs, 15);
    }
}

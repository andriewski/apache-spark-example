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

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles(AppProfile.LOCAL)
@SpringBootTest
public class SparkExecutionLocalTests {

    @Autowired
    SparkConf sparkConf;

    @Test
    void shouldCountRowsFromLocalFile() {
        //given:
        String fileName = "text.md";
        String pathToFile = requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getPath();
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> logData1 = sc.textFile(pathToFile).cache();
        JavaRDD<String> logData2 = sc.textFile(pathToFile).cache();

        //when:
        long numAs1 = SparkUtils.countLettersInRdd(logData1, 'a');
        long numBs1 = SparkUtils.countLettersInRdd(logData1, 'b');
        long numAs2 = SparkUtils.countLettersInRdd(logData2, 'a');
        long numBs2 = SparkUtils.countLettersInRdd(logData2, 'b');

        sc.close();

        //then:
        assertEquals(numAs1, 8);
        assertEquals(numBs1, 5);
        assertEquals(numAs2, 8);
        assertEquals(numBs2, 5);
    }
}

package by.mark.sparkexample.util;

import org.apache.spark.api.java.JavaRDD;

import java.util.Arrays;

public class SparkUtils {

    public static long countLettersInRdd(JavaRDD<String> rdd, Character letter) {
        return rdd.map(line -> line.split(""))
                .flatMap(letters -> Arrays.asList(letters).iterator())
                .filter(wordLetter -> letter.toString().equals(wordLetter))
                .count();
    }
}

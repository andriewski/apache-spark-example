package by.mark.sparkexample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SimpleApp {

    public static void main(String[] args) {
        String logFile = "/home/user/spark/README.md"; // Should be some file on your system
        SparkConf conf = new SparkConf()
                .setAppName("Simple Application")
                .setMaster("local[2]")
                .set("spark.executor.memory", "2g");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter(s -> s.contains("a")).count();
        long numBs = logData.filter(s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
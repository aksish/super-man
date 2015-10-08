package com.tektak.pagerank;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by tektak on 6/2/15.
 */
public class RankCalculator {
    public static class Pr2Mapper extends Mapper<Object, Text, Text, Text> {
        private static final double DAMPING = 0.85;
        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String[] arrayKeyValue = value.toString().split("\t");//split by tab
            String[] arrInLinks = arrayKeyValue[1].split(";");
            double rank = 0;
            for(String s : arrInLinks){
                String[] arrLinkNRank = s.split(",");
                String r = arrLinkNRank[1];
                rank = rank+Double.valueOf(r)*DAMPING;
            }
            DecimalFormat df = new DecimalFormat("0.000");
            rank = rank+(1-DAMPING);
            context.write(new Text(arrayKeyValue[0]),new Text(String.valueOf(df.format(rank))));
        }
    }


//    public static void main(String[] args) throws  IOException,ClassNotFoundException,InterruptedException{
//
//        Configuration conf = new Configuration();
//
//        Job job = Job.getInstance(conf, "MapReduce For PageRank");
//        job.setJarByClass(PageRankApp.class);
//        job.setMapperClass(Pr2Mapper.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//    }
}

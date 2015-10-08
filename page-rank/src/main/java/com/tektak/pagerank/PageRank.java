package com.tektak.pagerank;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.text.DecimalFormat;

public class PageRank{


    public static class Pr1Mapper extends Mapper<Object, Text, Text, Text> {
        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {


            String[] arrayKeyValue = value.toString().split("\t");//split by tab

//           // List<String> linksArray = new Gson().fromJson(arrayKeyValue[1].toString(), ArrayList.class);
//            String keyString = arrayKeyValue[0];
//            JsonObject jsonObject = (JsonObject)new JsonParser().parse(keyString);
//            Page page;

            String strLinkRank = arrayKeyValue[0];
            String[] arrLinkRank = strLinkRank.split(",");
            String link = arrLinkRank[0];
            double rank = Double.valueOf(arrLinkRank[1]);

            String allOutLinks = arrayKeyValue[1];
            String[] outLinksArray = allOutLinks.split(",");

            DecimalFormat df = new DecimalFormat("0.000");

            for(String out : outLinksArray){
                String forVal = "";
                forVal += link + "," + String.valueOf(df.format(rank/outLinksArray.length));
                context.write(new Text(out), new Text(forVal));
            }

        }

    }


    public static class Pr1Reducer extends Reducer<Text,Text,Text,Text> {

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {
            String strInlinnks = "";
          for(Text val : values){
                strInlinnks += val.toString() + ";";
            }
            strInlinnks = strInlinnks.substring(0, strInlinnks.length()-1);
            context.write(key, new Text(strInlinnks));
        }
    }

//    public static void main(String[] args) throws  IOException,ClassNotFoundException,InterruptedException{
//
//        Configuration conf = new Configuration();
//
//        Job job = Job.getInstance(conf, "MapReduce For PageRank");
//        job.setJarByClass(PageRankApp.class);
//        job.setMapperClass(Pr1Mapper.class);
//        job.setCombinerClass(Pr1Reducer.class);
//        job.setReducerClass(Pr1Reducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);
//    }
}
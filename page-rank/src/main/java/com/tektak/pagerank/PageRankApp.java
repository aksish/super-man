package com.tektak.pagerank;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * Created by tektak on 5/29/15.
 */
public class PageRankApp {

    public static class PrMapper extends Mapper<Object, Text, Text, Text> {

        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {

            String html = value.toString();

            Document doc = Jsoup.parse(html);

            Element link1 = doc.select("title").first();
            Elements links = doc.select("a[href]");//links with href

            for(Element link : links){

                String outLink = link.attr("href");
                String[] arrLink = outLink.split("#");

                if(arrLink[0].contains(".html")){
                    context.write(new Text(link1.text()+"," +0.5),new Text(arrLink[0]));
                }

            }



        }

    }
    public static class PrReducer extends Reducer<Text,Text,Text,Text> {
        Text result = new Text();

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {

            String allOutLinks = "";
            for (Text val : values) {
                allOutLinks += val.toString() + ",";
            }
            allOutLinks = allOutLinks.replaceAll(",$", "");
            result.set(allOutLinks);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws  IOException,ClassNotFoundException,InterruptedException{
//        Configuration conf = new Configuration();
//        conf.set("xmlinput.start", "<html>");
//        conf.set("xmlinput.end", "</html>");
//
//        Job job = Job.getInstance(conf, "MapReduce For PageRank");
//
//        job.setInputFormatClass(XmlInputFormat.class);
//        job.setJarByClass(PageRankApp.class);
//        job.setMapperClass(PrMapper.class);
//        job.setCombinerClass(PrReducer.class);
//        job.setReducerClass(PrReducer.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//
//        FileInputFormat.addInputPath(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        System.exit(job.waitForCompletion(true) ? 0 : 1);

        Configuration conf1 = new Configuration();
        conf1.set("xmlinput.start", "<html>");
        conf1.set("xmlinput.end", "</html>");

       // String[] otherArgs1 = new GenericOptionsParser(conf1, args).getRemainingArgs();

        ControlledJob cJob1 = new ControlledJob(conf1);

        cJob1.setJobName("Reading outlinks from html page.");
        Job job1 = cJob1.getJob();

        job1.setInputFormatClass(XmlInputFormat.class);
        job1.setJarByClass(PageRankApp.class);
        job1.setMapperClass(PrMapper.class);
        job1.setCombinerClass(PrReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        job1.setReducerClass(PrReducer.class);
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path("/data/result/chain1"));

        Configuration conf2 = new Configuration();



        ControlledJob cJob2 = new ControlledJob(conf2);

        cJob1.setJobName("Map reduce for inlinks");
        Job job2 = cJob2.getJob();
        cJob2.addDependingJob(cJob1);

        job2.setJarByClass(PageRank.class);
        job2.setMapperClass(PageRank.Pr1Mapper.class);
        job2.setCombinerClass(PageRank.Pr1Reducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        job2.setReducerClass(PageRank.Pr1Reducer.class);
        FileInputFormat.addInputPath(job2, new Path("/data/result/chain1"));
        FileOutputFormat.setOutputPath(job2, new Path("/data/result/chain2"));


        Configuration conf3 = new Configuration();



        ControlledJob cJob3 = new ControlledJob(conf3);

        cJob1.setJobName("Calculate pagerank");
        Job job3 = cJob3.getJob();
        cJob3.addDependingJob(cJob2);

        job3.setJarByClass(PageRank.class);
        job3.setMapperClass(RankCalculator.Pr2Mapper.class);

        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job3, new Path("/data/result/chain2"));
        FileOutputFormat.setOutputPath(job3, new Path(args[1]));

        JobControl control = new JobControl("Running PageRank Algorithm.....");
        control.addJob(cJob1);
        control.addJob(cJob2);
        control.addJob(cJob3);
        control.run();
        System.exit(control.allFinished()?1:0);
    }

}

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable>{

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                String token = itr.nextToken();
                if(token.indexOf(":") != -1){
                    // word.set(hour);
                    // context.write(word, one);
                    String hour = "";
                    if (token.length() > 3){
                        hour = token.substring(0,2);
                    }
                    switch(hour){
                        case "00":
                            word.set(hour);
                            break;
                        case "01":
                            word.set(hour);
                            break;
                        case "02":
                            word.set(hour);
                            break;
                        case "03":
                            word.set(hour);
                            break;
                        case "04":
                            word.set(hour);
                            break;
                        case "05":
                            word.set(hour);
                            break;
                        case "06":
                            word.set(hour);
                            break;
                        case "07":
                            word.set(hour);
                            break;
                        case "08":
                            word.set(hour);
                            break;
                        case "09":
                            word.set(hour);
                            break;
                        case "11":
                            word.set(hour);
                            break;
                        case "12":
                            word.set(hour);
                            break;
                        case "13":
                            word.set(hour);
                            break;
                        case "14":
                            word.set(hour);
                            break;
                        case "15":
                            word.set(hour);
                            break;
                        case "16":
                            word.set(hour);
                            break;
                        case "17":
                            word.set(hour);
                            break;
                        case "18":
                            word.set(hour);
                            break;
                        case "19":
                            word.set(hour);
                            break;
                        case "20":
                            word.set(hour);
                            break;
                        case "21":
                            word.set(hour);
                            break;
                        case "22":
                            word.set(hour);
                            break;
                        case "23":
                            word.set(hour);
                            break;
                        case "24":
                            word.set(hour);
                            break;
                        default:
                            hour = "error";
                            break;
                    }
                    if (!hour.equals("error")) {
                        context.write(word, one);
                    }
                }

            }
        }
    }

    public static class IntSumReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {

            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //FOR SECOND PART
        //conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", "\n\n");

        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
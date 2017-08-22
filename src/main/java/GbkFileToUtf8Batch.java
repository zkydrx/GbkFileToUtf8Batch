import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by ZKY on 2017-08-23 00:50.
 * 批量将GBK项目中的Java代码转换成UTF-8格式的代码
 */
public class GbkFileToUtf8Batch
{
    public static void main(String[] args) throws IOException
    {


        /**
         * 第一种是读取配置文件
         */
        Properties prop = new Properties();
        prop.load(GbkFileToUtf8Batch.class.getClassLoader().getResourceAsStream("param.properties"));
        //GBK编码格式源码路径
        String srcDirPath = prop.getProperty("gbkDirPath");
        //转为UTF-8编码格式源码路径
        String utf8DirPath = prop.getProperty("utf8DirPath");


        /**
         * 第二种是直接将路径写入代码中。
         */
//        //GBK编码格式源码路径
//        String srcDirPath = "C:\\Users\\zkydr\\Desktop\\CRM\\CRM\\src";
//        //转为UTF-8编码格式源码路径
//        String utf8DirPath = "C:\\Users\\zkydr\\Desktop\\CRM\\CRM\\srctemp";

        System.out.println("srcDirPath=" + srcDirPath + ",utf8DirPath=" + utf8DirPath);

        //获取所有java文件
        Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);

        for (File javaGbkFile : javaGbkFileCol)
        {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }

        System.out.println("批量编码完成.....");
    }
}

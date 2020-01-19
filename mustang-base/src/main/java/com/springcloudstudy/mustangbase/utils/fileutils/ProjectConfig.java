package com.springcloudstudy.mustangbase.utils.fileutils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 定义项目用到的全局参数，
 */
//@Component
@Data
public class ProjectConfig {
    //从服务器下载  文件地址

    public String DOWNLOADSRC;
    //上传到服务器  文件地址
    @Value("${uploadsrc}")
    public String UPLOADSRC;
    //aes秘钥  必须16位
    @Value("${aes_Key}")
    public String AES_KEY;
    //des秘钥  必须8位
    @Value("${des_Key}")
    public String DES_KEY;
    //ffmpeg地址      asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv
    // ffmpeg.exe的目录
    @Value("${ffmepg_src}")
    public String FFMPEGPATH;
    @Value("${mencoder_path}")
    public String MENCODER_PATH;
    //视频截屏存放地址
    @Value("${picture_src}")
    public String SCREENSHUTIMAGE_PATH;
    //转码后的文件存放地址
    @Value("${video_src}")
    public String video_src;
    //视频截图的大小
    @Value("${picture_size}")
    public String PICTURE_SIZE;


//    @PostConstruct
//    public static void init() {
//        InputStream inStream = Aes_Util.class.getClassLoader().getResourceAsStream("project.properties");
//        Properties prop = new Properties();
//        try {
//            prop.load(inStream);
//            PICTURE_SIZE = prop.getProperty("picture_size");
//            SCREENSHUTIMAGE_PATH = prop.getProperty("picture_src");
//            video_src = prop.getProperty("video_src");
//            DOWNLOADSRC = prop.getProperty("downloadSrc");
//            UPLOADSRC = prop.getProperty("uploadSrc");
//            AES_KEY = prop.getProperty("aes_Key");
//            DES_KEY = prop.getProperty("des_Key");
//            FFMPEGPATH = System.getProperty("user.dir") + prop.getProperty("ffmepg_src");
//            MENCODER_PATH = System.getProperty("user.dir") + prop.getProperty("mencoder_path");
//            File pictureDir = new File(SCREENSHUTIMAGE_PATH);
//            File videoDir = new File(video_src);
//            if (!pictureDir.exists()) {
//                pictureDir.mkdirs();
//            }
//            if (!videoDir.exists()) {
//                videoDir.mkdirs();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


}

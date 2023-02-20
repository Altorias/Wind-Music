package com.music.gu.constant;

public class Constant {

    // 性别 女为0男为1
    public static int GENDER_FEMALE = 0;
    public static int GENDER_MALE = 1;
    public static int GENDER_BAND = 2;

    // 项目路径
    public static String PROJECT_PATH = System.getProperty("user.dir");

    // 歌手路径
    public static String ARTIST_IMAGE_PATH = "file:" + PROJECT_PATH + "/static/image/artist/";

    // 佚名歌手图片
    public static String ANONYMOUS_ARTIST_IMAGE_PATH = "static/image/artist/anonymous.jpg";


}

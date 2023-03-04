package com.music.gu.constant;

public class Constant {

    // 性别 女为0男为1
    public static int GENDER_FEMALE = 0;
    public static int GENDER_MALE = 1;
    public static int GENDER_BAND = 2;

    // 项目路径
    public static String PROJECT_PATH = System.getProperty("user.dir");

    // 歌手图片路径
    public static String ARTIST_IMAGE_PATH = PROJECT_PATH + "/static/image/artist/";

    // 歌曲图片路径
    public static String MUSIC_IMAGE_PATH = PROJECT_PATH + "/static/image/music/";

    // 歌曲路径
    public static String MUSIC_PATH = PROJECT_PATH + "/static/music/";

    // 歌单图片路径
    public static String MUSIC_LIST_IMAGE_PATH = PROJECT_PATH + "/static/image/musicList/";




    // 佚名歌手图片
    public static String ANONYMOUS_ARTIST_IMAGE_PATH = "static/image/artist/anonymous.jpg";

    // 默认歌曲路径
    public static String DEFAULT_MUSIC_PATH = "static/music/BadApple.mp3";

    // 默认歌曲图片路径
    public static String DEFAULT_MUSIC_IMAGE_PATH = "static/image/music/default.jpg";

    // 默认歌单图片路径
    public static String DEFAULT_MUSIC_LIST_IMAGE_PATH = "static/image/music/default.jpg";



}

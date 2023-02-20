package com.music.gu.config;

import com.music.gu.constant.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticConfig implements WebMvcConfigurer {
    // 静态资源拦截器
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/image/artist/**")
                .addResourceLocations(Constant.ARTIST_IMAGE_PATH);
//        registry.addResourceHandler("/img/singerPic/**")
//                .addResourceLocations(Constants.SINGER_PIC_PATH);
//        registry.addResourceHandler("/img/songPic/**")
//                .addResourceLocations(Constants.SONG_PIC_PATH);
//        registry.addResourceHandler("/song/**")
//                .addResourceLocations(Constants.SONG_PATH);
//        registry.addResourceHandler("/img/songListPic/**")
//                .addResourceLocations(Constants.SONGLIST_PIC_PATH);
//        registry.addResourceHandler("/img/swiper/**")
//                .addResourceLocations(Constants.BANNER_PIC_PATH);
    }
}

package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.service.ArtistService;
import com.music.gu.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }


    @GetMapping("/getAllBanner")
    public ReturnMessage getAllBanner(){
        return ReturnMessage.success("成功获取轮播图与",bannerService.getActiveBanner());
    }
}

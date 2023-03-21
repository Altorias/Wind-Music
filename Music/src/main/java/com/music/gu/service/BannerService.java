package com.music.gu.service;

import com.music.gu.model.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author GCL
* @description 针对表【banner】的数据库操作Service
* @createDate 2023-03-14 18:12:04
*/
public interface BannerService extends IService<Banner> {

     List<Banner> getActiveBanner();

}

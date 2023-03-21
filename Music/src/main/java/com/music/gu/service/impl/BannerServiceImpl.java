package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.model.entity.Banner;
import com.music.gu.service.BannerService;
import com.music.gu.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author GCL
* @description 针对表【banner】的数据库操作Service实现
* @createDate 2023-03-14 18:12:04
*/
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner>
    implements BannerService{

    private final BannerMapper bannerMapper;

    public BannerServiceImpl(BannerMapper bannerMapper) {
        this.bannerMapper = bannerMapper;
    }

    @Cacheable(value = "banner", key = "'list'")  //放在缓存中 redis 是以key-value进行存储的
    @Override
    public List<Banner> getActiveBanner() {
        System.out.println("没有走缓存");
        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_active", 1);
        return bannerMapper.selectList(queryWrapper);
    }
}





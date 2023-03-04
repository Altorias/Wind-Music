package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.VMusic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author GCL
* @description 针对表【v_music】的数据库操作Service
* @createDate 2023-02-26 19:42:29
*/
public interface VMusicService extends IService<VMusic> {

    /**
     * 获取所有音乐
     * @return
     */
    ReturnMessage getAllMusic();



}

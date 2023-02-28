package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Music;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.model.request.MusicRequest;
import org.springframework.web.multipart.MultipartFile;

/**
* @author GCL
* @description 针对表【music】的数据库操作Service
* @createDate 2023-02-26 15:01:42
*/
public interface MusicService extends IService<Music> {

    /**
     * 添加音乐
     */
    ReturnMessage insertMusic(MusicRequest musicRequest);

    /**
     * 修改音乐
     */
    ReturnMessage updateMusicInfo(MusicRequest musicRequest);

    /**
     * 修改音乐图片
     */
    ReturnMessage updateMusicPic(MultipartFile imgFile, int id);

    /**
     * 修改音乐文件
     */
    ReturnMessage updateMusicUrl(MultipartFile imgFile, int id);

    /**
     * 删除音乐
     */
    ReturnMessage deleteMusic(Integer id);

}

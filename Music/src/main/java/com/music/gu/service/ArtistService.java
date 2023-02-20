package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Artist;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.gu.model.request.ArtistRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author GCL
 * @description 针对表【artist】的数据库操作Service
 * @createDate 2023-02-12 18:21:17
 */
public interface ArtistService extends IService<Artist> {

    /**
     * 获取所有艺术家
     *
     * @return
     */
    ReturnMessage getArtistList();

    /**
     * 添加艺术家
     */
    ReturnMessage addArtist(ArtistRequest artistRequest);

    /**
     * 修改艺术家
     */
    ReturnMessage updateArtist(ArtistRequest artistRequest);

    /**
     * 修改艺术家图片
     */
    ReturnMessage updateArtistPic(MultipartFile imgFile, int id);


    /**
     * 删除艺术家
     * todo
     * 删除时要将其歌曲变成默认值
     */
    ReturnMessage deleteArtist(Integer id);

}

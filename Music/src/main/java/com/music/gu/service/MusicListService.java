package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.MusicList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.model.request.MusicListRequest;
import org.springframework.web.multipart.MultipartFile;

/**
* @author GCL
* @description 针对表【play_list】的数据库操作Service
* @createDate 2023-02-28 22:20:32
*/
public interface MusicListService extends IService<MusicList> {

    ReturnMessage getAllMusicList();

    /**
     * 添加艺术家
     */
    ReturnMessage insertMusicList(MusicListRequest musicListRequest);


    /**
     * 修改艺术家
     */
    ReturnMessage updateMusicList(MusicListRequest musicListRequest);

    /**
     * 修改艺术家图片
     */
    ReturnMessage updateMusicListPic(MultipartFile imgFile, int id);


    /**
     * 删除艺术家
     */
    ReturnMessage deleteMusicList(Integer id);

}

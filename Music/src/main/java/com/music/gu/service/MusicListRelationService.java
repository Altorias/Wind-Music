package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.MusicListRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.model.request.MusicListRelationRequest;
import org.springframework.web.multipart.MultipartFile;

/**
* @author GCL
* @description 针对表【music_list_relation】的数据库操作Service
*/
public interface MusicListRelationService extends IService<MusicListRelation> {

    ReturnMessage insertMusicToList(MusicListRelationRequest musicListRelationRequest);

    ReturnMessage deleteMusicFromList(Integer id);


    /**
     * 获取不在歌单的所有音乐
     * @return
     */
    ReturnMessage getMusicNotInList(Integer id);


}

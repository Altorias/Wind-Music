package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.VMusicRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author GCL
* @description 针对表【v_music_relation】的数据库操作Service
* @createDate 2023-03-04 18:09:57
*/
public interface VMusicRelationService extends IService<VMusicRelation> {
    ReturnMessage selectMusicByListId(Integer id);


}

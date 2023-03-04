package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Music;
import com.music.gu.model.entity.MusicListRelation;
import com.music.gu.model.entity.VMusic;
import com.music.gu.service.VMusicService;
import com.music.gu.mapper.VMusicMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author GCL
* @description 针对表【v_music】的数据库操作Service实现
* @createDate 2023-02-26 19:42:29
*/
@Service
public class VMusicServiceImpl extends ServiceImpl<VMusicMapper, VMusic>
    implements VMusicService{

    private final VMusicMapper vMusicMapper;

    public VMusicServiceImpl(VMusicMapper vMusicMapper) {
        this.vMusicMapper = vMusicMapper;
    }

    @Override
    public ReturnMessage getAllMusic() {
        return ReturnMessage.success(null, vMusicMapper.selectList(null));
    }


}





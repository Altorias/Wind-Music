package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Music;
import com.music.gu.model.entity.MusicListRelation;
import com.music.gu.model.entity.VMusicRelation;
import com.music.gu.service.VMusicRelationService;
import com.music.gu.mapper.VMusicRelationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GCL
 * @description 针对表【v_music_relation】的数据库操作Service实现
 * @createDate 2023-03-04 18:09:57
 */
@Service
public class VMusicRelationServiceImpl extends ServiceImpl<VMusicRelationMapper, VMusicRelation>
        implements VMusicRelationService {

    private final VMusicRelationMapper vMusicRelationMapper;

    public VMusicRelationServiceImpl(VMusicRelationMapper vMusicRelationMapper) {
        this.vMusicRelationMapper = vMusicRelationMapper;
    }


    @Override
    public ReturnMessage selectMusicByListId(Integer id) {
        QueryWrapper<VMusicRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("list_id", id);
        return ReturnMessage.success(null, vMusicRelationMapper.selectList(queryWrapper));
    }


}





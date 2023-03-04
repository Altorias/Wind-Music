package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.mapper.MusicListMapper;
import com.music.gu.mapper.MusicListRelationMapper;
import com.music.gu.mapper.MusicMapper;
import com.music.gu.mapper.VMusicMapper;
import com.music.gu.model.entity.Music;
import com.music.gu.model.entity.MusicListRelation;
import com.music.gu.model.entity.VMusic;
import com.music.gu.model.request.MusicListRelationRequest;
import com.music.gu.service.MusicListRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GCL
 * @description 针对表【play_list_relation】的数据库操作Service实现
 * @createDate 2023-03-02 12:33:27
 */
@Service
public class MusicListRelationServiceImpl extends ServiceImpl<MusicListRelationMapper, MusicListRelation>
        implements MusicListRelationService {

    private final MusicListRelationMapper musicListRelationMapper;
    private final VMusicMapper vMusicMapper;

    public MusicListRelationServiceImpl(MusicListRelationMapper musicListRelationMapper, VMusicMapper vMusicMapper) {
        this.musicListRelationMapper = musicListRelationMapper;
        this.vMusicMapper = vMusicMapper;
    }


    @Override
    public ReturnMessage insertMusicToList(MusicListRelationRequest musicListRelationRequest) {
        MusicListRelation musicListRelation = new MusicListRelation();
        BeanUtils.copyProperties(musicListRelationRequest, musicListRelation);
        if (musicListRelationMapper.insert(musicListRelation) > 0) {
            return ReturnMessage.success("添加成功");
        } else {
            return ReturnMessage.error("添加失败");
        }
    }

    @Override
    public ReturnMessage deleteMusicFromList(Integer id) {
        if (musicListRelationMapper.deleteById(id) > 0) {
            return ReturnMessage.success("删除成功");
        } else {
            return ReturnMessage.error("删除失败");
        }
    }

    @Override
    public ReturnMessage getMusicNotInList(Integer id) {
        QueryWrapper<VMusic> musicQueryWrapper = new QueryWrapper<>();
        QueryWrapper<MusicListRelation> musicListRelationQueryWrapper = new QueryWrapper<>();
        musicListRelationQueryWrapper.eq("music_list_id", id);
        List<MusicListRelation> musicListRelationList =
                musicListRelationMapper.selectList(musicListRelationQueryWrapper);
        List<Integer> musicIdList =
                musicListRelationList.stream().map(MusicListRelation::getMusicId).collect(Collectors.toList());
        musicQueryWrapper.notIn("id", musicIdList);
        return ReturnMessage.success(null, vMusicMapper.selectList(musicQueryWrapper));
    }
}





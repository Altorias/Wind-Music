package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Song;
import com.music.gu.service.SongService;
import com.music.gu.mapper.SongMapper;
import org.springframework.stereotype.Service;

/**
 * @author GCL
 * @description 针对表【song】的数据库操作Service实现
 * @createDate 2023-02-13 07:53:41
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
        implements SongService {

    private final SongMapper songMapper;

    public SongServiceImpl(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    public ReturnMessage selectSongByArtistId(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("artist_id", id);
        return ReturnMessage.success(null, songMapper.selectList(queryWrapper));
    }
}





package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.mapper.SongMapper;
import com.music.gu.model.entity.Artist;
import com.music.gu.model.entity.Song;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.service.ArtistService;
import com.music.gu.mapper.ArtistMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.music.gu.constant.Constant.ANONYMOUS_ARTIST_IMAGE_PATH;

/**
 * @author GCL
 * @description 针对表【artist】的数据库操作Service实现
 * @createDate 2023-02-12 18:21:17
 */
@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, Artist>
        implements ArtistService {

    private final ArtistMapper artistMapper;
    private final SongMapper songMapper;

    public ArtistServiceImpl(ArtistMapper artistMapper, SongMapper songMapper) {
        this.artistMapper = artistMapper;
        this.songMapper = songMapper;
    }

    @Override
    public ReturnMessage getArtistList() {
        return ReturnMessage.success(null, artistMapper.selectList(null));
    }

    @Override
    public ReturnMessage addArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        BeanUtils.copyProperties(artistRequest, artist);
        artist.setPic(ANONYMOUS_ARTIST_IMAGE_PATH);
        if (artistMapper.insert(artist) > 0) {
            return ReturnMessage.success("添加成功");
        } else {
            return ReturnMessage.error("添加失败");
        }
    }

    @Override
    public ReturnMessage updateArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        BeanUtils.copyProperties(artistRequest, artist);
        if (artistMapper.updateById(artist) > 0) {
            return ReturnMessage.success("添加成功");
        } else {
            return ReturnMessage.error("添加失败");
        }
    }

    @Override
    public ReturnMessage updateArtistPic(MultipartFile imgFile, int id) {
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "static" + System.getProperty("file.separator") + "image"
                + System.getProperty("file.separator") + "artist";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        String fileName = System.currentTimeMillis() + imgFile.getOriginalFilename();
        File finalFile = new File(filePath + System.getProperty("file.separator") + fileName);
        try {
            imgFile.transferTo(finalFile);
        } catch (IOException e) {
            return ReturnMessage.fatal("上传失败" + e.getMessage());
        }
        String imgPath = "static/image/artist/" + fileName;
        Artist artist = new Artist();
        artist.setId(id);
        artist.setPic(imgPath);
        if (artistMapper.updateById(artist) > 0) {
            return ReturnMessage.success("上传成功", imgPath);
        } else {
            return ReturnMessage.error("上传失败");
        }
    }

    @Override
    public ReturnMessage deleteArtist(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("artist_id", id);
        List<Song> songList = songMapper.selectList(queryWrapper);
        try {
            if (songList != null && songList.size() > 0) {
                for (Song song : songList) {
                    //将歌曲的歌手设置为佚名
                    song.setArtistId(1);
                    songMapper.updateById(song);
                }
            }
            if (artistMapper.deleteById(id) > 0) {
                return ReturnMessage.success("删除成功");
            } else {
                return ReturnMessage.error("删除失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ReturnMessage.error("删除失败");
        }
    }
}





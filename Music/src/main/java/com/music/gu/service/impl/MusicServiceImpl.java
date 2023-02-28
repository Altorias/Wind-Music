package com.music.gu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Artist;
import com.music.gu.model.entity.Music;
import com.music.gu.model.request.MusicRequest;
import com.music.gu.service.MusicService;
import com.music.gu.mapper.MusicMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.music.gu.constant.Constant.*;

/**
* @author GCL
* @description 针对表【music】的数据库操作Service实现
* @createDate 2023-02-26 15:01:42
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService{

    private MusicMapper musicMapper;

    public MusicServiceImpl(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }


    @Override
    public ReturnMessage insertMusic(MusicRequest musicRequest) {
        Music music = new Music();
        BeanUtils.copyProperties(musicRequest, music);
        music.setUrl(DEFAULT_MUSIC_PATH);
        music.setPic(DEFAULT_MUSIC_IMAGE_PATH);
        if (musicMapper.insert(music) > 0) {
            return ReturnMessage.success("上传成功");
        } else {
            return ReturnMessage.error("上传失败");
        }
    }

    @Override
    public ReturnMessage updateMusicInfo(MusicRequest musicRequest) {
        Music music = new Music();
        BeanUtils.copyProperties(musicRequest, music);
        if (musicMapper.updateById(music) > 0) {
            return ReturnMessage.success("修改成功");
        } else {
            return ReturnMessage.error("修改失败");
        }
    }

    @Override
    public ReturnMessage updateMusicPic(MultipartFile imgFile, int id) {
        if (imgFile.isEmpty() || StringUtils.isEmpty(imgFile.getOriginalFilename())) {
            return ReturnMessage.error("添加失败");
        }
        String filePath = MUSIC_IMAGE_PATH;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        String originFileName = imgFile.getOriginalFilename();
        String fileAfterFix = originFileName.substring(originFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis() + fileAfterFix;
        File finalFile = new File(filePath + System.getProperty("file.separator") + fileName);
        try {
            imgFile.transferTo(finalFile);
        } catch (IOException e) {
            return ReturnMessage.fatal("上传失败" + e.getMessage());
        }
        String imgPath = "static/image/music/" + fileName;
        Music music = new Music();
        music.setId(id);
        music.setPic(imgPath);
        if (musicMapper.updateById(music) > 0) {
            return ReturnMessage.success("上传成功", imgPath);
        } else {
            return ReturnMessage.error("上传失败");
        }
    }

    @Override
    public ReturnMessage updateMusicUrl(MultipartFile musicFile, int id) {
        if (musicFile.isEmpty() || StringUtils.isEmpty(musicFile.getOriginalFilename())) {
            return ReturnMessage.error("添加失败");
        }
        String filePath = MUSIC_PATH;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        String originFileName = musicFile.getOriginalFilename();
        String fileAfterFix = originFileName.substring(originFileName.lastIndexOf("."));
        String fileName = System.currentTimeMillis() + fileAfterFix;
        File finalFile = new File(filePath + System.getProperty("file.separator") + fileName);
        try {
            musicFile.transferTo(finalFile);
        } catch (IOException e) {
            return ReturnMessage.fatal("上传失败" + e.getMessage());
        }
        String musicPath = "static/music/" + fileName;
        Music music = new Music();
        music.setId(id);
        music.setUrl(musicPath);
        if (musicMapper.updateById(music) > 0) {
            return ReturnMessage.success("上传成功", musicPath);
        } else {
            return ReturnMessage.error("上传失败");
        }
    }

    @Override
    public ReturnMessage deleteMusic(Integer id) {
        if (musicMapper.deleteById(id) > 0) {
            return ReturnMessage.success("删除成功");
        } else {
            return ReturnMessage.error("删除失败");
        }
    }
}





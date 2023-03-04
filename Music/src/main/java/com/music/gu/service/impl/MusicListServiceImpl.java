package com.music.gu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.mapper.MusicListMapper;
import com.music.gu.model.entity.Artist;
import com.music.gu.model.entity.MusicList;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.model.request.MusicListRequest;
import com.music.gu.service.MusicListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.music.gu.constant.Constant.DEFAULT_MUSIC_LIST_IMAGE_PATH;
import static com.music.gu.constant.Constant.MUSIC_LIST_IMAGE_PATH;

/**
* @author GCL
* @description 针对表【play_list】的数据库操作Service实现
* @createDate 2023-02-28 22:20:32
*/
@Service
public class MusicListServiceImpl extends ServiceImpl<MusicListMapper, MusicList>
    implements MusicListService {

    private final MusicListMapper musicListMapper;

    public MusicListServiceImpl(MusicListMapper musicListMapper) {
        this.musicListMapper = musicListMapper;
    }


    @Override
    public ReturnMessage getAllMusicList() {
        return ReturnMessage.success(null, musicListMapper.selectList(null));
    }

    @Override
    public ReturnMessage insertMusicList(MusicListRequest musicListRequest) {
        MusicList musicList = new MusicList();
        BeanUtils.copyProperties(musicListRequest, musicList);
        musicList.setPic(DEFAULT_MUSIC_LIST_IMAGE_PATH);
        if (musicListMapper.insert(musicList) > 0) {
            return ReturnMessage.success("添加成功");
        } else {
            return ReturnMessage.error("添加失败");
        }
    }

    @Override
    public ReturnMessage updateMusicList(MusicListRequest musicListRequest) {
        MusicList musicList = new MusicList();
        BeanUtils.copyProperties(musicListRequest, musicList);
        if (musicListMapper.updateById(musicList) > 0) {
            return ReturnMessage.success("修改成功");
        } else {
            return ReturnMessage.error("修改失败");
        }
    }

    @Override
    public ReturnMessage updateMusicListPic(MultipartFile imgFile, int id) {
        if (imgFile.isEmpty() || StringUtils.isEmpty(imgFile.getOriginalFilename())) {
            return ReturnMessage.error("添加失败");
        }
        String filePath = MUSIC_LIST_IMAGE_PATH;
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
        String musicListPicPath = "static/image/musicList/" + fileName;
        MusicList musicList = new MusicList();
        musicList.setId(id);
        musicList.setPic(musicListPicPath);
        if (musicListMapper.updateById(musicList) > 0) {
            return ReturnMessage.success("上传成功", musicListPicPath);
        } else {
            return ReturnMessage.error("上传失败");
        }
    }

    @Override
    public ReturnMessage deleteMusicList(Integer id) {
        if (musicListMapper.deleteById(id) > 0) {
            return ReturnMessage.success("删除成功");
        } else {
            return ReturnMessage.error("删除失败");
        }
    }
}





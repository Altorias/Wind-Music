package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.model.request.MusicListRequest;
import com.music.gu.service.ArtistService;
import com.music.gu.service.MusicListService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MusicListController {

    private final MusicListService musicListService;

    public MusicListController(MusicListService musicListService) {
        this.musicListService = musicListService;
    }

    @GetMapping("/musicList/getAllMusicList")
    public ReturnMessage getAllMusicList() {
        return musicListService.getAllMusicList();
    }


    @PostMapping("/musicList/insertMusicList")
    public ReturnMessage insertMusicList(@Validated MusicListRequest musicListRequest) {
        return musicListService.insertMusicList(musicListRequest);
    }

    @PostMapping("/musicList/updateMusicListInfo")
    public ReturnMessage updateMusicList(@Validated MusicListRequest musicListRequest) {
        return musicListService.updateMusicList(musicListRequest);
    }

    @GetMapping("/musicList/deleteMusicList")
    public ReturnMessage deleteMusicList(@RequestParam int id) {
        return musicListService.deleteMusicList(id);
    }


    //todo
    @PostMapping("/static/image/musicList/update")
    public ReturnMessage updateArtistPic(@RequestParam("file") MultipartFile imgFile, @RequestParam("id") int id) {
        return musicListService.updateMusicListPic(imgFile, id);
    }

}

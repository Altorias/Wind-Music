package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.model.request.MusicRequest;
import com.music.gu.service.MusicService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MusicController {
    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/music/insertMusic")
    public ReturnMessage insertMusic(@Validated MusicRequest musicRequest) {
        return musicService.insertMusic(musicRequest);
    }

    @PostMapping("/music/updateMusicInfo")
    public ReturnMessage updateMusicInfo(@Validated MusicRequest musicRequest) {
        return musicService.updateMusicInfo(musicRequest);
    }

    @GetMapping("/music/deleteMusic")
    public ReturnMessage deleteMusic(@RequestParam int id) {
        return musicService.deleteMusic(id);
    }


    @PostMapping("/static/image/music/update")
    public ReturnMessage updateMusicPic(@RequestParam("file") MultipartFile imgFile, @RequestParam("id") int id) {
        return musicService.updateMusicPic(imgFile, id);
    }

    @PostMapping("/static/music/update")
    public ReturnMessage updateMusicUrl(@RequestParam("file") MultipartFile musicFile, @RequestParam("id") int id) {
        return musicService.updateMusicUrl(musicFile, id);
    }

}

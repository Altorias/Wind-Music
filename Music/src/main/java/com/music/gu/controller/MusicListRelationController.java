package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.request.MusicListRelationRequest;
import com.music.gu.service.MusicListRelationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicListRelationController {

    private final MusicListRelationService musicListRelationService;


    public MusicListRelationController(MusicListRelationService musicListRelationService) {
        this.musicListRelationService = musicListRelationService;
    }

    @PostMapping("/musicListDetail/insertMusicToList")
    public ReturnMessage insertMusicToList(@Validated MusicListRelationRequest musicListRelationRequest) {
        return musicListRelationService.insertMusicToList(musicListRelationRequest);
    }

    @GetMapping("/musicListDetail/deleteMusicFromList")
    public ReturnMessage deleteMusicFromList(@RequestParam int id) {
        return musicListRelationService.deleteMusicFromList(id);
    }

    @GetMapping("/musicListDetail/getMusicNotInList")
    public ReturnMessage getMusicNotInList(@RequestParam int id) {
        return musicListRelationService.getMusicNotInList(id);
    }



}

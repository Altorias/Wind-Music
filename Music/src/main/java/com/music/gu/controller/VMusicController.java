package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;

import com.music.gu.service.VMusicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VMusicController {
    private final VMusicService vMusicService;

    public VMusicController(VMusicService vMusicService) {
        this.vMusicService = vMusicService;
    }

    @GetMapping("/music/getAllMusic")
    public ReturnMessage getAllArtist() {
        return vMusicService.getAllMusic();
    }
}

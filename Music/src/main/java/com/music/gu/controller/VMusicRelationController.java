package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.service.VMusicRelationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VMusicRelationController {

    private final VMusicRelationService vMusicRelationService;


    public VMusicRelationController(VMusicRelationService vMusicRelationService) {
        this.vMusicRelationService = vMusicRelationService;
    }

    @GetMapping("/musicListDetail/selectMusicByListId")
    public ReturnMessage selectMusicByListId(int id) {
        return vMusicRelationService.selectMusicByListId(id);
    }

}

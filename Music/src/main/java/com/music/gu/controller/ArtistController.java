package com.music.gu.controller;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.request.ArtistRequest;
import com.music.gu.service.ArtistService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artist/getArtistList")
    public ReturnMessage getArtistList() {
        return artistService.getArtistList();
    }


    @PostMapping("/artist/addArtist")
    public ReturnMessage addArtist(@Validated ArtistRequest artistRequest){
        return artistService.addArtist(artistRequest);
    }

    @PostMapping("/artist/updateArtist")
    public ReturnMessage updateArtist(@Validated ArtistRequest artistRequest){
        return artistService.updateArtist(artistRequest);
    }

    @GetMapping("/artist/deleteArtist")
    public ReturnMessage deleteArtist(@RequestParam int id) {
        return artistService.deleteArtist(id);
    }


    //todo
    @PostMapping("/static/image/singer/update")
    public ReturnMessage updateArtistPic(@RequestParam("file") MultipartFile imgFile, @RequestParam("id") int id){
        return artistService.updateArtistPic(imgFile, id);
    }


}

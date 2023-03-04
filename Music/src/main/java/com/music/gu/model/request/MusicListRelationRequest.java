package com.music.gu.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MusicListRelationRequest {

    private Integer id;

    @NotNull(message = "歌单ID不能为空")
    private Integer musicListId;

    @NotNull(message = "音乐ID不能为空")
    private Integer musicId;
}

package com.music.gu.model.request;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MusicRequest {

    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    private String album;

    private Date createTime;

    private Date updateTime;

    private String pic;

    private String url;

    @NotNull(message = "歌手不能为空")
    private Integer artistId;

    private String lyrics;
}

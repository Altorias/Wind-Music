package com.music.gu.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
public class MusicListRequest {

    private Integer id;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String pic;

    private String introduction;

    private String tag;

}

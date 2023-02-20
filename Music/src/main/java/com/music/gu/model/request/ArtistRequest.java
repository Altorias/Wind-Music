package com.music.gu.model.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ArtistRequest {

    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;


    private Integer gender;


    private String pic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    private String location;


    private String description;
}

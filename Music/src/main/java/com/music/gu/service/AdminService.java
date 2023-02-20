package com.music.gu.service;

import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.gu.model.request.AdminRequest;

import javax.servlet.http.HttpSession;

/**
* @author GCL
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-02-10 23:08:09
*/
public interface AdminService extends IService<Admin> {

    ReturnMessage getLoginStatus(AdminRequest adminRequest, HttpSession session);

}

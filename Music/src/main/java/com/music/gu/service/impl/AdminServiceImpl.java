package com.music.gu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.gu.common.ReturnMessage;
import com.music.gu.model.entity.Admin;
import com.music.gu.model.request.AdminRequest;
import com.music.gu.service.AdminService;
import com.music.gu.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
* @author GCL
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-02-10 23:08:09
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    @Override
    public ReturnMessage getLoginStatus(AdminRequest adminRequest, HttpSession session) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", adminRequest.getUsername());
        queryWrapper.eq("password", adminRequest.getPassword());
        if (adminMapper.selectCount(queryWrapper) > 0) {
            session.setAttribute("username", adminRequest.getUsername());
            return ReturnMessage.success("登录成功");
        } else {
            return ReturnMessage.error("用户名或密码错误");
        }
    }
}





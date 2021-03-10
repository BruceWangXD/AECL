package com.aecl.demo.service;


import com.aecl.demo.entity.Admin;
import com.aecl.demo.entity.JsonResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminService {

    JsonResult login(HttpServletResponse response, String username, String password);

    String getEmailById(Integer uid);
}

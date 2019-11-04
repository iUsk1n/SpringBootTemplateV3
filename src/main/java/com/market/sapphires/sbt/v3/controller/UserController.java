package com.market.sapphires.sbt.v3.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.market.sapphires.sbt.v3.constants.Page;
import com.market.sapphires.sbt.v3.constants.Url;
import com.market.sapphires.sbt.v3.entity.LoginUser;
import com.market.sapphires.sbt.v3.form.datatables.request.UsersDataTablesRequest;
import com.market.sapphires.sbt.v3.form.datatables.response.UserDataTables;
import com.market.sapphires.sbt.v3.mapper.LoginUserMapper;
import com.market.sapphires.sbt.v3.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = Url.USERS)
public class UserController {

    private final UserService service;

    private final LoginUserMapper dao;

    @GetMapping(value = Url.USERS_INFO)
    public String info(Model model) {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", this.service.getDetail(user.getId()));

        // TODO TargetNotFoundException

        return Page.USERS_INFO;
    }

    @GetMapping(value = Url.USERS_LIST)
    public String list(Model model) {
        return Page.USERS_LIST;
    }

    @GetMapping(value = "/getList")
    @ResponseBody
    public UserDataTables getList(@RequestParam Map<String, String> params, HttpServletRequest request) {
        return this.service.getList(new UsersDataTablesRequest(request));
    }

    /*
    @GetMapping(value = "/{id}")
    public String show(Model model, @PathVariable long id) {
        model.addAttribute("user", this.service.getDetail(id));
    
        // TODO ex NoSuch~
    
        return "users/show";
    }
    */

}

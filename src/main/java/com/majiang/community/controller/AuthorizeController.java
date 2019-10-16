package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_id("cd1f051064fb18319fce");
        accessTokenDTO.setClient_secret("ee7df96fdd6f768199cdf75af9a03846e0b92bdb");
        String acceessToken = githubProvider.getAcceessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(acceessToken);
        System.out.println(user.getName());


        return "index";
    }
}

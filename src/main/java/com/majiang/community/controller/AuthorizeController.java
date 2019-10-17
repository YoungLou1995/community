package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.ProgressBarUI;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;


    @Value("${github.redirect.uri}")
    private String githubRedirectUri;

    @Value("${github.client.id}")
    private String githubClientId;

    @Value("${github.client.secret}")
    private String githubClientSecret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(githubRedirectUri);
        accessTokenDTO.setClient_id(githubClientId);
        accessTokenDTO.setClient_secret(githubClientSecret);
        String acceessToken = githubProvider.getAcceessToken(accessTokenDTO);
        // Get user infos from github with accessToken
        GithubUser githubUser = githubProvider.getUser(acceessToken);
        if (githubUser != null){
            // 登录成功，写cookie，和session

            // Store user infos to DB
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.inset(user);

            // Add Cookie
            response.addCookie(new Cookie("token", token));

            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        }else{
            // 登录失败，重新登录
            return "redirect:/";
        }
    }
}

package com.majiang.community.controller;

import com.majiang.community.mapper.QuestionMapper;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.Question;
import com.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
//
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

//    @PostMapping("/publish")
//    public String doPublish(
//            @RequestParam("title") String title,
//            @RequestParam("description") String description,
//            @RequestParam("tag") String tag,
//            HttpServletRequest request,
//            Model model){
//        User user = null;
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")){
//                String token = cookie.getValue();
//                user = userMapper.findByToken(token);
//                if (user != null){
//                    request.getSession().setAttribute("user", user);
//                }
//                break;
//            }
//        }
//
//        if (user == null){
//            model.addAttribute("error", "haven't login yet!" );
//            return "publish";
//        }
//        Question question = new Question();
//        question.setTitle(title);
//        question.setDescription(description);
//        question.setTag(String.valueOf(tag));
//        question.setCreator(user.getId());
//        question.setGmtCreate(user.getGmtCreate());
//        question.setGmtModified(user.getGmtModified());
//
//        questionMapper.create(question);
//        return "publish";
//    }

}

package com.gowid.leo.api.web.controller

import com.gowid.leo.api.config.auth.LoginUser
import com.gowid.leo.api.config.auth.SessionUser
import com.gowid.leo.api.service.posts.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
class IndexController (
        private val postsService: PostsService,
        private val httpSession: HttpSession
){

    @GetMapping("/")
    fun index(model : Model, @LoginUser sessionUser: SessionUser) : String {
        model.addAttribute("posts", postsService.findAllDesc())

        sessionUser?.let {
            model.addAttribute("userName", sessionUser.name)
        }

        return "index";
    }
}
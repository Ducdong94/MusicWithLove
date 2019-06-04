package com.suggestion.service.controller;

import com.suggestion.service.model.request.BodyLogin;
import com.suggestion.service.model.request.BodyRegiter;
import com.suggestion.service.model.response.Result;
import com.suggestion.service.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenications")
public class AuthenicationController extends BaseController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody BodyLogin bodyLogin){
        logger.info("Request <== {}",bodyLogin);
        Result result = loginService.login(bodyLogin);
        logger.info("Response ==>{}",result);
        return result;
    }

    @PostMapping("/register")
    public Result rsgister(@RequestBody BodyRegiter bodyRegiter){
        logger.info("Request <== {}",bodyRegiter);
        Result result = loginService.register(bodyRegiter);
        logger.info("Response ==>{}",result);
        return result;
    }


}

package com.demo.controller;

import com.demo.mapper.LoginMapper;
import com.demo.model.UserDao;
import com.demo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@Slf4j
public class LoginController {
  @Autowired
  LoginService loginService;
  @Autowired
  LoginMapper loginMapper;


  @GetMapping(value = {"/hello"})
  public String hello() {
    return "Hello World";
  }

  @PostMapping(value = "/sortnum")
  public int[] sortArrayAscending(@RequestParam("array") String inputArr) {
    String[] stringArr = inputArr.split(",");
    int n = stringArr.length;
    int[] arr = new int[n];
    for (int a = 0; a < n; a++) {
      arr[a] = Integer.valueOf(stringArr[a]);
    }

    for (int i = 0; i < n-1; i++) {
      for (int j = 0; j < n-i-1; j++) {
        if (arr[j] > arr[j+1]) {
          int temp = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = temp;
        }
      }
    }
    return arr;
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
    String token = null;
    try {
      UserDao userInfo = loginMapper.getUserInfo(username, password);

      // If the credentials match, generate an access token
      if (userInfo.getUsername().equals("admin") && userInfo.getPassword().equals("Admin&8181")) {
        token = UUID.randomUUID().toString();

        session.setAttribute("loginSession", token);
        session.setMaxInactiveInterval(24 * 60 * 60 * 1000);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return token;
  }

  @GetMapping("/is_auth")
  public void isAuth() {
//    可以試試看spring boot security
  }



}

package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    MemberRepository mRepo;

    @Autowired
    JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> params, HttpServletResponse res) {
        // * member service로 바꾸고 카톡 연동 적용하기
        Member member = mRepo.findByEmailAndPassword(params.get("email"), params.get("password"));
        if (member != null) {
            int id = member.getId();
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token",token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    
    //JWT 토큰
    @GetMapping("/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());

            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

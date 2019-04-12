package com.example.spring.login.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalControllerAdvice {

    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        model.addAttribute("error", "内部サーバエラー（DB） :ExceptionHandler");
        model.addAttribute("message","SignupControllerでDataAccessExceptionが発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // 例外クラスのメッセージをModelに登録
        model.addAttribute("error", "内部サーバエラー :ExceptionHandler");
        model.addAttribute("message","SignupControllerでExceptionが発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}

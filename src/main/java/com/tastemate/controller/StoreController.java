package com.tastemate.controller;

import com.tastemate.domain.StoreVO;
import com.tastemate.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/store/*")
@Slf4j
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping("/list")
    public void get(Model model){

        List<StoreVO> storeVO = service.store_getList();

        model.addAttribute("storeList", storeVO);

       // return "/store/list";
    }

    @GetMapping("/get")
    public void get(int storeIdx, Model model){
        log.info("get storeIdx :" + storeIdx);

        StoreVO storeVO = service.store_get(storeIdx);
        model.addAttribute("storeVO", storeVO);

    }

    @GetMapping("/register")
    public void register(){

    }


    @PostMapping("/register")
    public String registerStoreVO(StoreVO storeVO, MultipartFile oriFilename){

        log.info("controller도착");
        service.saveFile(storeVO, oriFilename);

        return "redirect:/store/list";
    }



}

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
import org.springframework.web.bind.annotation.RequestParam;
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
    public void get(Model model
             ,@RequestParam(value="cuisineSelect",required = false) String cuisineSelect){

        if(cuisineSelect == null){
            cuisineSelect = "없음";
        }

        log.info("cuisineSelect : " + cuisineSelect);

        List<StoreVO> storeVO = service.store_getList(cuisineSelect);

        model.addAttribute("storeList", storeVO);

    }

    @GetMapping({"/get", "/update"})
    public void get(int storeIdx, Model model){
        log.info("get 또는 update storeIdx :" + storeIdx);

        StoreVO storeVO = service.store_get(storeIdx);
        StoreVO storeVO1 = service.store_getWithStar(storeIdx);
        StoreVO storeVO2 = service.store_getWithComment(storeIdx);

        model.addAttribute("storeVO", storeVO);
        model.addAttribute("storeVO_star", storeVO1);
        model.addAttribute("storeVO_comment", storeVO2);

    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String registerStoreVO(StoreVO storeVO, MultipartFile oriFilename){

        service.saveFile(storeVO, oriFilename);

        return "redirect:/store/list";
    }



    @PostMapping("/update")
    public String updateStoreVO(StoreVO storeVO, MultipartFile oriFilename){

        log.info("Controller storeVO : " + storeVO);
        service.updateFile(storeVO, oriFilename);


        return "redirect:/store/list";
    }

    @GetMapping("/delete")
    public String delete(int storeIdx){

        log.info("delete storeIdx: " + storeIdx) ;
        int result = service.store_delete(storeIdx);
        log.info("delete 완료 : " + result) ;

        return "redirect:/store/list";
    }

}

package com.tastemate.service;

import com.tastemate.domain.StoreVO;
import com.tastemate.mapper.StoreMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StoreService {

    @Autowired
    private StoreMapper mapper;

    @Value("${file.dir}")
    private String fileDir;



    public List<StoreVO> store_getList(String cuisineSelect) {

        //List<StoreVO> storeList = mapper.store_getList();
        List<StoreVO> storeList = mapper.store_getList_withStar(cuisineSelect);

        return  storeList;
    }

    public StoreVO store_get(int storeIdx) {

        //StoreVO storeVO = mapper.store_get(storeIdx);
        StoreVO storeVO = mapper.getStoreWithMenu(storeIdx);

        return storeVO;
    }

    public StoreVO store_getWithStar(int storeIdx) {

        StoreVO storeVO = mapper.getStoreWithStar(storeIdx);

        return storeVO;
    }

    public StoreVO store_getWithComment(int storeIdx) {

        StoreVO storeVO = mapper.getStoreWithComment(storeIdx);

        return storeVO;
    }


    public void saveFile(StoreVO storeVO, MultipartFile multipartFile) {

        String savedName = null;

        if(multipartFile.isEmpty()){
            savedName = "tastemate.jpg";

        } else {

        String oriFilename = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();

        String extension = oriFilename.substring(oriFilename.lastIndexOf("."));

        oriFilename = oriFilename.substring(oriFilename.lastIndexOf("\\")+1);

        savedName = uuid + "_" + oriFilename;

        String savedPath = fileDir + "/" +savedName;
       
        log.info(savedPath);

        File saveFile = new File(savedPath);

        try {
            multipartFile.transferTo(saveFile);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        } //else 끝


        StoreVO storeVO1 = new StoreVO();


        storeVO1.setFilename(savedName);
        //storeVO1.setFilename(savedPath);
        storeVO1.setUserIdx(storeVO.getUserIdx());
        storeVO1.setStoreName(storeVO.getStoreName());
        storeVO1.setCategory1(storeVO.getCategory1());
        storeVO1.setStoreAddress(storeVO.getStoreAddress());
        storeVO1.setStoreLati(storeVO.getStoreLati());
        storeVO1.setStoreLongi(storeVO.getStoreLongi());
        storeVO1.setPhoneNumber(storeVO.getPhoneNumber());

        //log.info("Service 잘 변환되었나?" + storeVO1);

        int result = mapper.store_register(storeVO1);

    }

    public int store_delete(int storeIdx) {

        int result = mapper.store_delete(storeIdx);

        return result;
    }

    public void updateFile(StoreVO storeVO, MultipartFile multipartFile) {


        StoreVO storeVO1 = new StoreVO();
        String savedName = null;


        if (multipartFile.getOriginalFilename().equals("")){
            //새 파일 없을때
            savedName = storeVO.getFilename();

        } else if(multipartFile.getOriginalFilename() != null) {

            File saveFile = new File(storeVO.getFilename());

            if (saveFile.exists()){
                saveFile.delete();
            }

            String oriFilename = multipartFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String extension = oriFilename.substring(oriFilename.lastIndexOf("."));

            oriFilename = oriFilename.substring(oriFilename.lastIndexOf("\\")+1);

            savedName = uuid + "_" + oriFilename;

            String savedPath = fileDir + "/" +savedName;

            log.info(savedPath);

            saveFile = new File(savedPath);

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }


        storeVO1.setFilename(savedName);
        //storeVO1.setFilename(savedPath);
        storeVO1.setStoreIdx(storeVO.getStoreIdx());
        storeVO1.setUserIdx(storeVO.getUserIdx());
        storeVO1.setStoreName(storeVO.getStoreName());
        storeVO1.setCategory1(storeVO.getCategory1());
        storeVO1.setStoreAddress(storeVO.getStoreAddress());
        storeVO1.setStoreLati(storeVO.getStoreLati());
        storeVO1.setStoreLongi(storeVO.getStoreLongi());
        storeVO1.setPhoneNumber(storeVO.getPhoneNumber());

        log.info("Service 잘 변환되었나?" + storeVO1);

        int result = mapper.store_update(storeVO1);
    }
}

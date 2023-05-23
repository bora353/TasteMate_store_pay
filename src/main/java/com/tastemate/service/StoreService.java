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



    public List<StoreVO> store_getList() {

        List<StoreVO> storeList = mapper.store_getList();

        return  storeList;
    }

    public StoreVO store_get(int storeIdx) {

        StoreVO storeVO = mapper.store_get(storeIdx);
        return storeVO;
    }


    public void saveFile(StoreVO storeVO, MultipartFile multipartFile) {

            if(multipartFile.isEmpty()){
                return;
            }

            String oriFilename = multipartFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();

            String extension = oriFilename.substring(oriFilename.lastIndexOf("."));

            oriFilename = oriFilename.substring(oriFilename.lastIndexOf("\\")+1);

            String savedName = uuid + "_" + oriFilename;

            String savedPath = fileDir + savedName;

            File saveFile = new File(fileDir, savedName);

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            StoreVO storeVO1 = new StoreVO();


            storeVO1.setUserIdx(storeVO.getUserIdx());
            storeVO1.setStoreName(storeVO.getStoreName());
            storeVO1.setCategory1(storeVO.getCategory1());
            storeVO1.setStoreAddress(storeVO.getStoreAddress());
            storeVO1.setStoreLati(storeVO.getStoreLati());
            storeVO1.setStoreLongi(storeVO.getStoreLongi());
            storeVO1.setPhoneNumber(storeVO.getPhoneNumber());

            log.info("Service 잘 변환되었나?" + storeVO1);

            int result = mapper.store_register(storeVO1);

    }
}

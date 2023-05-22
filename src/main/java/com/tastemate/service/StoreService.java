package com.tastemate.service;

import com.tastemate.domain.StoreVO;
import com.tastemate.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreMapper mapper;


    public List<StoreVO> store_getList() {

        List<StoreVO> storeList = mapper.store_getList();

        return  storeList;
    }

    public StoreVO store_get(int storeIdx) {

        StoreVO storeVO = mapper.store_get(storeIdx);
        return storeVO;
    }
}

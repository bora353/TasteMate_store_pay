package com.tastemate.mapper;

import com.tastemate.domain.StoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper {

    //List<StoreVO> store_getList();
    List<StoreVO> store_getList_withStar(Map<String,String> orderMap);
    StoreVO store_get(int storeIdx);


    int store_register(StoreVO storeVO);
    int store_update(StoreVO storeVO1);
    int store_delete(int storeIdx);



    //join
    StoreVO getStoreWithMenu(int storeIdx);
    StoreVO getStoreWithComment(int storeIdx);
    StoreVO getStoreWithStar(int storeIdx);



}

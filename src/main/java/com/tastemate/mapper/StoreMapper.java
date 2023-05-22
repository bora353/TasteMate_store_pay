package com.tastemate.mapper;

import com.tastemate.domain.StoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface StoreMapper {

    public List<StoreVO> store_getList();
    StoreVO store_get(int storeIdx);

}

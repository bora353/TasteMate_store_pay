package com.tastemate.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("StarVO")
public class StarVO {

    private int starIdx;
    private int storeIdx;
    private int userIdx;
    private double storeStar;
    private int userStar;

    private String storeComment;


}

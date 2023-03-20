package com.example.emos.api.service;

import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.db.pojo.TbReim;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yuxuyao
 * @date 2022/11/28 - 22:38
 */
public interface ReimService {
    PageUtils searchReimByPage(HashMap param);

    int insert(TbReim reim);

    HashMap searchReimById(HashMap param);

    int deleteReimById(HashMap param);

}

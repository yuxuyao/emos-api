package com.example.emos.api.service;

import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.db.pojo.TbAmect;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yuxuyao
 * @date 2022/11/26 - 16:29
 */
public interface AmectService {
    public PageUtils searchAmectByPage(HashMap param);

    int insert (ArrayList<TbAmect> list);

    HashMap searchById(int id);

    int update(HashMap param);

    int deleteAmectByIds(Integer[] ids);

    HashMap searchChart(HashMap param);
}

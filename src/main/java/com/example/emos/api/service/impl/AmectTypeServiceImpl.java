package com.example.emos.api.service.impl;

import cn.hutool.core.map.MapUtil;
import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.db.dao.TbAmectTypeDao;
import com.example.emos.api.db.pojo.TbAmectType;
import com.example.emos.api.service.AmectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AmectTypeServiceImpl implements AmectTypeService {
    @Autowired
    private TbAmectTypeDao amectTypeDao;

    @Override
    public ArrayList<TbAmectType> searchAllAmectType() {
        ArrayList<TbAmectType> list = amectTypeDao.searchAllAmectType();
        return list;
    }

    @Override
    public PageUtils searchAmectTypePage(HashMap param) {
        ArrayList<HashMap> list = amectTypeDao.searchAmectTypePage(param);
        long count = amectTypeDao.searchAmectTypeCount(param);
        Integer start = MapUtil.getInt(param, "start");
        Integer length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    public int insert(TbAmectType amectType) {
        int rows = amectTypeDao.insert(amectType);
        return rows;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap list = amectTypeDao.searchById(id);
        return list;
    }

    @Override
    public int update(HashMap param) {
        int rows = amectTypeDao.update(param);
        return rows;
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        int rows = amectTypeDao.deleteByIds(ids);
        return rows;
    }
}
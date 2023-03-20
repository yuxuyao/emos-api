package com.example.emos.api.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.map.MapUtil;
import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.db.dao.TbAmectDao;
import com.example.emos.api.db.pojo.TbAmect;
import com.example.emos.api.service.AmectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author yuxuyao
 * @date 2022/11/26 - 16:30
 */
@Service
@Slf4j
public class AmectServiceImpl implements AmectService {
    @Autowired
    private TbAmectDao amectDao;
    @Override
    public PageUtils searchAmectByPage(HashMap param) {
        ArrayList<HashMap> list = amectDao.searchAmectByPage(param);
        long count = amectDao.searchAmectCount(param);
        Integer start = (Integer)param.get("start");
        Integer length = (Integer)param.get("length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    @Transactional
    public int insert(ArrayList<TbAmect> list) {
        list.forEach(item -> {
            amectDao.insert(item);
        });
        return list.size();
    }

    @Override
    public HashMap searchById(int id) {
        HashMap list = amectDao.searchById(id);
        return list;
    }

    @Override
    public int update(HashMap param) {
        int rows = amectDao.update(param);
        return rows;
    }

    @Override
    public int deleteAmectByIds(Integer[] ids) {
        int rows = amectDao.deleteAmectByIds(ids);
        return rows;
    }

    @Override
    public HashMap searchChart(HashMap param) {
        ArrayList<HashMap> chart_1 = amectDao.searchChart_1(param);
        ArrayList<HashMap> chart_2 = amectDao.searchChart_2(param);
        ArrayList<HashMap> chart_3 = amectDao.searchChart_3(param);
        param.clear();
        int year = DateUtil.year(new Date());
        param.put("year",year);
        param.put("status",1);
        ArrayList<HashMap> list_1 = amectDao.searchChart_4(param);
        param.replace("status",2);
        ArrayList<HashMap> list_2 = amectDao.searchChart_4(param);

        //保存着12个月
        ArrayList<HashMap> chart_4_1 = new ArrayList<>();
        ArrayList<HashMap> chart_4_2 = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            HashMap map = new HashMap();
            map.put("month",i);
            map.put("ct",0);
            chart_4_1.add(map);
            chart_4_2.add((HashMap) map.clone());
        }
        //将list_1中的数据copy到chart_4_1中
        list_1.forEach(one -> {
            chart_4_1.forEach(item ->{
                if (MapUtil.getInt(one,"month").equals(MapUtil.getInt(item,"month"))){
                    item.replace("ct",MapUtil.getInt(one,"ct"));
                }
            });
        });
        //将list_2中的数据copy到chart_4_2中
        list_2.forEach(one -> {
            chart_4_2.forEach(item ->{
                if (MapUtil.getInt(one,"month").equals(MapUtil.getInt(item,"month"))){
                    item.replace("ct",MapUtil.getInt(one,"ct"));
                }
            });
        });

        return new HashMap(){{
                put("chart_1",chart_1);
                put("chart_2",chart_2);
                put("chart_3",chart_3);
                put("chart_4_1",chart_4_1);
                put("chart_4_2",chart_4_2);
        }};

    }
}

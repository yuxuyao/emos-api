package com.example.emos.api.db.dao;

import com.example.emos.api.db.pojo.TbAmectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbAmectTypeDao {
    public ArrayList<TbAmectType> searchAllAmectType();

    ArrayList<HashMap> searchAmectTypePage(HashMap param);

    long searchAmectTypeCount(HashMap param);

    int insert(TbAmectType amectType);

    HashMap searchById(int id);

    int update(HashMap param);

    int deleteByIds(Integer[] ids);
}

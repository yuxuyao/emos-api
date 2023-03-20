package com.example.emos.api.db.dao;

import com.example.emos.api.db.pojo.TbAmect;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbAmectDao {
    ArrayList<HashMap> searchAmectByPage(HashMap param);
    long searchAmectCount(HashMap param);

    int insert(TbAmect amect);

    HashMap searchById(int id);

    int update(HashMap param);

    int deleteAmectByIds(Integer[] ids);

    ArrayList<HashMap> searchChart_1(HashMap param);
    ArrayList<HashMap> searchChart_2(HashMap param);
    ArrayList<HashMap> searchChart_3(HashMap param);
    ArrayList<HashMap> searchChart_4(HashMap param);
}

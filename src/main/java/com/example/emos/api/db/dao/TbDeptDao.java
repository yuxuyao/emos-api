package com.example.emos.api.db.dao;

import com.example.emos.api.db.pojo.TbDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface TbDeptDao {
    public ArrayList<HashMap> searchAllDept();
    public HashMap searchById(int id);

    ArrayList<HashMap> searchDeptByPage(HashMap param);
    long searchDeptCount(HashMap param);

    int insert(TbDept dept);

    int update(TbDept dept);

    int deleteDeptByIds(Integer[] ids);

    boolean searchCanDelete(Integer[] ids);

}
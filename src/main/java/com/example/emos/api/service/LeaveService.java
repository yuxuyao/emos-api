package com.example.emos.api.service;

import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.db.pojo.TbLeave;

import java.util.HashMap;

/**
 * @author yuxuyao
 * @date 2022/11/28 - 15:50
 */
public interface LeaveService {
    PageUtils searchLeaveByPage(HashMap param);

    boolean searchContradiction(HashMap param);

    int insert(TbLeave leave);
    int deleteLeaveById(HashMap param);

    HashMap searchLeaveById(HashMap param);
}

package com.example.emos.api.service;

import com.example.emos.api.common.util.PageUtils;

import java.util.HashMap;

/**
 * @author yuxuyao
 * @date 2022/11/24 - 10:18
 */
public interface ApprovalService {
    PageUtils searchTaskByPage(HashMap param);

    HashMap searchApprovalContent(HashMap param);

    void approvalTask(HashMap param);
}

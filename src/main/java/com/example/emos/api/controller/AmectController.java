package com.example.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.common.util.R;
import com.example.emos.api.controller.form.*;
import com.example.emos.api.db.pojo.TbAmect;
import com.example.emos.api.service.AmectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yuxuyao
 * @date 2022/11/26 - 16:37
 */
@RestController
@Slf4j
@RequestMapping("/amect")
@Tag(name = "AmectController",description = "罚款Web接口")
public class AmectController {
    @Autowired
    private AmectService amectService;

    @PostMapping("/searchAmectByPage")
    @Operation(summary = "查询罚款分页记录")
    @SaCheckLogin
    public R searchAmectByPage(@Valid @RequestBody SearchAmectByPageForm form){
        if ((form.getStartDate() != null && form.getEndDate() ==null) || (form.getStartDate() == null && form.getEndDate() !=null)){
            return R.error("startDate和endDate只能同时为空，或者不为空");
        }
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);

        int start = (form.getPage() - 1) * form.getLength();
        param.put("start",start);
        param.put("currentUserId", StpUtil.getLoginIdAsInt());
        if (!(StpUtil.hasPermission("AMECT:SELECT") || StpUtil.hasPermission("ROOT"))){
            param.put("userId",StpUtil.getLoginIdAsInt());
        }
        PageUtils pageUtils = amectService.searchAmectByPage(param);
        return R.ok().put("page",pageUtils);
    }

    @PostMapping("/insert")
    @Operation(summary = "添加罚款记录")
    @SaCheckPermission(value = {"ROOT","AMECT:INSERT"},mode = SaMode.OR)
    public R insert(@Valid @RequestBody InsertAmectForm form){
        ArrayList<TbAmect> list = new ArrayList<>();
        for (Integer userId : form.getUserId()) {
            TbAmect amect = new TbAmect();
            amect.setAmount(new BigDecimal(form.getAmount()));
            amect.setTypeId(form.getTypeId());
            amect.setUserId(userId);
            amect.setReason(form.getReason());
            amect.setUuid(IdUtil.simpleUUID());
            list.add(amect);
        }
        int rows = amectService.insert(list);
        return R.ok().put("rows",rows);
    }


    @PostMapping("/searchById")
    @Operation(summary = "根据ID查找罚款记录")
    public R searchById(@Valid @RequestBody SearchAmectByIdForm form){
        HashMap map = amectService.searchById(form.getId());
        return R.ok(map);
    }




    @PostMapping("/update")
    @Operation(summary = "更新罚款记录")
    @SaCheckPermission(value = {"ROOT","AMECT:UPDATE"},mode = SaMode.OR)
    public R update(@Valid @RequestBody UpdateAmectForm form){
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = amectService.update(param);
        return R.ok().put("rows",rows);
    }

    @PostMapping("/deleteAmectByIds")
    @Operation(summary = "删除罚款记录")
    @SaCheckPermission(value = {"ROOT","AMECT:DELETE"},mode = SaMode.OR)
    public R deleteAmectByIds(@Valid @RequestBody DeleteAmectByIdsForm form){
        int rows = amectService.deleteAmectByIds(form.getIds());
        return R.ok().put("rows",rows);
    }

    @PostMapping("/searchChart")
    @Operation(summary = "查询Chart图表")
    @SaCheckPermission(value = {"ROOT","AMECT:SELECT"},mode = SaMode.OR)
    public R searchChart(@Valid @RequestBody SearchChartForm form){
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        HashMap map = amectService.searchChart(param);
        return R.ok(map);
    }
}




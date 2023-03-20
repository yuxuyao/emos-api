package com.example.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.json.JSONUtil;
import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.common.util.R;
import com.example.emos.api.controller.form.*;
import com.example.emos.api.db.pojo.TbAmectType;
import com.example.emos.api.service.AmectTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/amect_type")
@Tag(name = "AmectTypeController", description = "罚款类型Web接口")
public class AmectTypeController {
    @Autowired
    private AmectTypeService amectTypeService;

    @GetMapping("/searchAllAmectType")
    @Operation(summary = "查询所有罚款类型")
    @SaCheckLogin
    public R searchAllAmectType() {
        ArrayList<TbAmectType> list = amectTypeService.searchAllAmectType();
        return R.ok().put("list", list);
    }

    @PostMapping("/searchAmectTypeByPage")
    @Operation(summary = "查询罚款类型分页记录")
    @SaCheckPermission(value = {"ROOT"})
    public R searchAmectTypeByPage(@Valid @RequestBody SearchAmectTypeByPageForm form){
        int start = (form.getPage() - 1) * form.getLength();
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start",start);
        PageUtils pageUtils = amectTypeService.searchAmectTypePage(param);
        return R.ok().put("page",pageUtils);
    }

    @PostMapping("/insert")
    @Operation(summary = "添加罚款类型")
    @SaCheckPermission(value = {"ROOT"})
    public R insert(@Valid @RequestBody InsertAmectTypeForm form){
        TbAmectType amectType = JSONUtil.parse(form).toBean(TbAmectType.class);
        int rows = amectTypeService.insert(amectType);
        return R.ok().put("rows",rows);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查找罚款类型")
    @SaCheckPermission(value = {"ROOT"})
    public R searchById(@Valid @RequestBody SearchAmectTypeByIdForm form){
        HashMap map = amectTypeService.searchById(form.getId());
        return R.ok(map);
    }

    @PostMapping("/update")
    @Operation(summary = "更新罚款类型")
    @SaCheckPermission(value = {"ROOT"})
    public R update(@Valid @RequestBody UpdateAmectTypeByIdForm form){
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = amectTypeService.update(param);
        return R.ok().put("rows",rows);
    }

    @PostMapping("/deleteAmectTypeByIds")
    @Operation(summary = "删除罚款类型")
    @SaCheckPermission(value = {"ROOT"})
    public R deleteAmectTypeByIds(@Valid @RequestBody DeleteAmectTypeByIdsForm form){
        int rows = amectTypeService.deleteByIds(form.getIds());
        return R.ok().put("rows",rows);
    }
}

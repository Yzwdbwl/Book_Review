package com.project.app.basic.controller;

import com.project.app.basic.entity.SysRole;
import com.project.app.basic.service.SysRoleService;
import com.project.app.model.dto.PageDTO;
import com.project.app.util.R;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sysRole")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService iSysRoleService;


    @PostMapping("page")
    @Operation(summary = "角色表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iSysRoleService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "角色表列表")
    public R listAll() {
        return R.success(iSysRoleService.listAll());
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "角色表详情")
    public R getSysRoleById(@PathVariable("id") String id) {
        return R.success(iSysRoleService.getSysRoleById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "角色表删除")
    public R deleteSysRoleById(@PathVariable("id") String id) {
        return R.success(iSysRoleService.deleteSysRoleById(id), "删除成功");
    }

    @DeleteMapping("deleteByIds")
    @Operation(summary = "角色表批量删除")
    public R deleteSysRoleById(@RequestBody List<String> ids) {
        return R.success(iSysRoleService.deleteSysRoleByIds(ids), "删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "角色表新增")
    public R addSysRole(@RequestBody SysRole obj) {
        return R.success(iSysRoleService.addSysRole(obj), "新增成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "角色表更新")
    public R updateSysRoleById(@RequestBody SysRole obj) {
        return R.success(iSysRoleService.updateSysRoleById(obj), "更新成功");
    }


}

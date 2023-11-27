package com.project.app.basic.controller;

import com.project.app.basic.entity.SysUser;
import com.project.app.basic.service.SysUserService;
import com.project.app.model.dto.PageDTO;
import com.project.app.util.R;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SysUserController {

    private final SysUserService iSysUserService;


    @PostMapping("page")
    @Operation(summary = "用户表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iSysUserService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "用户表列表")
    public R listAll() {
        return R.success(iSysUserService.listAll());
    }

    @GetMapping("listAllByRole")
    @Operation(summary = "通过角色获取用户表列表")
    public R listAllByRole(String role) {
        return R.success(iSysUserService.listAllByRole(role));
    }


    @GetMapping("getById/{id}")
    @Operation(summary = "用户表详情")
    public R getSysUserById(@PathVariable("id") String id) {
        return R.success(iSysUserService.getSysUserById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "用户表删除")
    public R deleteSysUserById(@PathVariable("id") String id) {
        return R.success(iSysUserService.deleteSysUserById(id), "删除成功");
    }

    @DeleteMapping("deleteByIds")
    @Operation(summary = "用户表批量删除")
    public R deleteSysUserById(@RequestBody List<String> ids) {
        return R.success(iSysUserService.deleteSysUserByIds(ids), "删除成功");
    }


    @PostMapping("add")
    @Operation(summary = "用户表新增")
    public R addSysUser(@RequestBody SysUser obj) {
        return R.success(iSysUserService.addSysUser(obj), "新增成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "用户表更新")
    public R updateSysUserById(@RequestBody SysUser obj) {
        return R.success(iSysUserService.updateSysUserById(obj), "更新成功");
    }


    @PutMapping("updateSysUserByName")
    @Operation(summary = "重置密码")
    public R updateSysUserByName(@RequestBody SysUser obj) {
        return R.success(iSysUserService.updateSysUserByName(obj), "重置成功");
    }


}

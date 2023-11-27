package com.project.app.basic.controller;

import com.project.app.basic.entity.SysAuthority;
import com.project.app.basic.entity.SysRole;
import com.project.app.basic.service.SysAuthorityService;
import com.project.app.model.dto.PageDTO;
import com.project.app.util.R;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sysAuthority")
@RequiredArgsConstructor
public class SysAuthorityController {

    private final SysAuthorityService iSysAuthorityService;


    @PostMapping("page")
    @Operation(summary = "权限表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iSysAuthorityService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "权限表列表")
    public R listAll() {
        return R.success(iSysAuthorityService.listAll());
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "权限表详情")
    public R getSysAuthorityById(@PathVariable("id") String id) {
        return R.success(iSysAuthorityService.getSysAuthorityById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "权限表删除")
    public R deleteSysAuthorityById(@PathVariable("id") String id) {
        return R.success(iSysAuthorityService.deleteSysAuthorityById(id), "删除成功");
    }

    @DeleteMapping("deleteByIds")
    @Operation(summary = "权限表批量删除")
    public R deleteSysAuthorityById(@RequestBody List<String> ids) {
        return R.success(iSysAuthorityService.deleteSysAuthorityByIds(ids), "删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "权限表新增")
    public R addSysAuthority(@RequestBody SysAuthority obj) {
        return R.success(iSysAuthorityService.addSysAuthority(obj), "新增成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "权限表更新")
    public R updateSysAuthorityById(@RequestBody SysAuthority obj) {
        return R.success(iSysAuthorityService.updateSysAuthorityById(obj), "更新成功");
    }



    @GetMapping("curryAuthority")
    @Operation(summary = "角色表更新")
    public R curryAuthority(String roleName) {
        return R.success(iSysAuthorityService.curryAuthority(roleName));
    }

}

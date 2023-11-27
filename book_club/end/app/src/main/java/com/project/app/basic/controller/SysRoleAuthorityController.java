package com.project.app.basic.controller;

import com.project.app.util.R;
import com.project.app.basic.entity.SysRoleAuthority;
import com.project.app.basic.service.SysRoleAuthorityService;
import com.project.app.model.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2023
 */
@RestController
@RequestMapping("/sysRoleAuthority")
@RequiredArgsConstructor
    public class SysRoleAuthorityController {

    private final SysRoleAuthorityService iSysRoleAuthorityService;


    @PostMapping("page")
    @Operation(summary = "角色权限关联表分页")
    public R page(@RequestBody PageDTO pageDTO) {
        return R.success(iSysRoleAuthorityService.page(pageDTO));
    }

    @GetMapping("listAll")
    @Operation(summary = "角色权限关联表列表")
    public R listAll() {
        return R.success(iSysRoleAuthorityService.listAll());
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "角色权限关联表详情")
    public R getSysRoleAuthorityById(@PathVariable("id") String id) {
        return R.success(iSysRoleAuthorityService.getSysRoleAuthorityById(id));
    }

    @DeleteMapping("deleteById/{id}")
    @Operation(summary = "角色权限关联表删除")
    public R deleteSysRoleAuthorityById(@PathVariable("id") String id) {
        return R.success(iSysRoleAuthorityService.deleteSysRoleAuthorityById(id),"删除成功");
    }

    @DeleteMapping("deleteByIds")
    @Operation(summary = "角色权限关联表批量删除")
    public R deleteSysRoleAuthorityById(@RequestBody List<String> ids) {
        return R.success(iSysRoleAuthorityService.deleteSysRoleAuthorityByIds(ids),"删除成功");
    }

    @PostMapping("add")
    @Operation(summary = "角色权限关联表新增")
    public R addSysRoleAuthority(@RequestBody SysRoleAuthority obj) {
        return R.success(iSysRoleAuthorityService.addSysRoleAuthority(obj),"新增成功");
    }


    @PutMapping("updateById")
    @Operation(summary = "角色权限关联表更新")
    public R updateSysRoleAuthorityById(@RequestBody SysRoleAuthority obj) {
        return R.success(iSysRoleAuthorityService.updateSysRoleAuthorityById(obj),"更新成功");
    }

}

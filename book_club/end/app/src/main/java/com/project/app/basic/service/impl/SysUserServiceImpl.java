package com.project.app.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.app.basic.entity.SysUser;
import com.project.app.basic.mapper.SysUserMapper;
import com.project.app.basic.service.SysUserService;
import com.project.app.model.dto.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2023
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public Page<SysUser> page(PageDTO pageDTO) {

        return lambdaQuery()
                .like(StringUtils.hasText(pageDTO.getSearch()), SysUser::getUsername, pageDTO.getSearch())
                .orderByDesc(SysUser::getCreateTime).page(pageDTO.getMybatisPage());


    }

    @Override
    public List<SysUser> listAll() {
        return list();
    }

    @Override
    public List<SysUser> listAllByRole(String role) {
        return lambdaQuery()
                .eq(SysUser::getRole, role)
                .list();
    }


    @Override
    public SysUser getSysUserById(String id) {
        return getById(id);
    }

    @Override
    public boolean deleteSysUserById(String id) {
        return removeById(id);
    }

    @Override
    public boolean deleteSysUserByIds(List<String> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public boolean addSysUser(SysUser obj) {
        return save(obj);
    }

    @Override
    public boolean updateSysUserById(SysUser obj) {
        return updateById(obj);
    }

    @Override
    public boolean updateSysUserByName(SysUser obj) {
        return lambdaUpdate()
                .eq(SysUser::getUsername, obj.getUsername())
                .set(SysUser::getPassword, obj.getPassword())
                .update();
    }


}

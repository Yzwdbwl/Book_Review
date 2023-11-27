package com.project.app.util;

import com.project.app.basic.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static SysUser getCurrentUser() {
        return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

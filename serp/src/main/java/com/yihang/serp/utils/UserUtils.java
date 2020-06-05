package com.yihang.serp.utils;

import com.yihang.serp.model.Users;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static Users getCurrentUser() {
        return ((Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

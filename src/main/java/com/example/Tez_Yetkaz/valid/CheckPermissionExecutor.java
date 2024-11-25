package com.example.Tez_Yetkaz.valid;

import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.exception.ForbiddenException;
import com.example.Tez_Yetkaz.util.MessageKey;
import com.example.Tez_Yetkaz.util.MessageService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermission)")
    public void beforeCheckPermission(CheckPermission checkPermission) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean exist = false;
        for (GrantedAuthority authority : principal.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                exist = true;
                break;
            }
        }
        if (!exist) throw new ForbiddenException(checkPermission.value(), MessageService.getMessage(MessageKey.FORBIDDEN));
    }

}

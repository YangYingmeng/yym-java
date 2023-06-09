package com.yym.security.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @Description: 账户信息
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023-05-19 19:46
 */
@Getter
@Setter
@ToString
public class AccountDetails {

    private String account;
    private String password;
    private String companyCode;
    private Set<String> authorities;

    public AccountDetails() {

    }
    public AccountDetails(String account) {
        this.account = account;
    }

    // 判断是否有权限
    public boolean hasPermission(String  authority) {
        return this.authorities.contains(authority);
    }

    public boolean hasAnyPermission(String... authorities) {
        for (String authority : authorities) {
            if (hasPermission(authority)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllPermission(String... authorities) {
        for (String authority : authorities) {
            if (!hasPermission(authority)) {
                return false;
            }
        }
        return true;
    }
}

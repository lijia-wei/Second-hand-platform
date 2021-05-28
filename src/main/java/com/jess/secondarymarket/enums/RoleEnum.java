package com.jess.secondarymarket.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @author Jess
 * @date 2020/7/31 9:48
 */
@Getter
public enum RoleEnum {
    USER(1, "用户"),
    ADMIN(2, "管理员"),
    ;

    private Integer value;
    private String role;

    RoleEnum(Integer value, String role) {
        this.value = value;
        this.role = role;
    }

    public static String getRole(Integer integer) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(ADMIN.getValue(), ADMIN.getRole());
        hashMap.put(USER.getValue(), USER.getRole());
        return hashMap.get(integer);
    }

    public static Integer getValue(String role) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(ADMIN.getRole(), ADMIN.getValue());
        hashMap.put(USER.getRole(), USER.getValue());
        return hashMap.get(role);
    }
}
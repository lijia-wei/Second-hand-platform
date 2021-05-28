package com.jess.secondarymarket.accessctro;

import com.jess.secondarymarket.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: secondarymarket
 * @description: TODO
 * @author: Jess
 * @create: 2021-05-27 07:50
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleContro {
    RoleEnum role();
}

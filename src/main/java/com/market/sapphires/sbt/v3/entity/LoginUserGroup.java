package com.market.sapphires.sbt.v3.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class LoginUserGroup implements Serializable {

    private int id;

    private String name;

    private Set<LoginUser> users = new HashSet<>();

    private Set<LoginUserPermission> permissions = new HashSet<>();

    private int version;

}

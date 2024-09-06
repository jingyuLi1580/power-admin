package com.example.powerAdmin.enums;

public enum AuthorityType {
    ALL("所有权限", "定义哪些角色或用户拥有资源（内容或素材等）的所有权限"),
    READ("可查看者", "定义哪些角色或用户可以查看资源（内容或素材等）"),
    WRITE("可修改者", "定义哪些角色或用户可以修改资源（内容或素材等）"),
    DOWNLOAD("可下载者", "定义哪些角色或用户可以下载资源（内容或素材等）"),
    SHARE("可分享者", "定义哪些角色或用户可以分享资源（内容或素材等）"),
    INVISIBLE("不可见", "定义哪些角色或用户不能见到资源（内容或素材等），类似黑名单的作用");

    private String name; // 权限名称
    private String description; // 权限描述

    AuthorityType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "AuthorityType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
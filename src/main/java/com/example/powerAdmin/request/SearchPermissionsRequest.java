package com.example.powerAdmin.request;

import lombok.Data;

@Data
public class SearchPermissionsRequest {
    /**
     * 权限Id
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限类型（功能权限/数据权限）
     */
    private Long permissionType;

    /**
     * 资源ID（对应菜单或数据表ID）
     */
    private String resourceId;

    /**
     * 资源类型（菜单/数据表）
     */
    private Long resourceType;
}

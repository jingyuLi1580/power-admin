package com.example.powerAdmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author ljy
 * @since 2023-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Permissions implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;

    /**
     * 修改者
     */
    private String editor;

    /**
     * 是否删除（0-否，1-是）
     */
    private Long deleted;


}

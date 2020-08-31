package xdclass.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限实体类
 */
@Data
public class Permission implements Serializable {
    private int id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 接口路径
     */
    private String url;
}

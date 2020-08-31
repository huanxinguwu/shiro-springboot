package xdclass.shiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 */
@Data
public class Role implements Serializable {
    private int id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 角色描述
     */
    private String desc;
    private List<Permission> permissionList;


}

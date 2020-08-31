package xdclass.shiro.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermission implements Serializable {

    private int id;
    private int roleId;
    private int permissionId;

}

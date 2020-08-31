package xdclass.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关联实体类
 */
@Data
public class UserRole  implements Serializable {
    private int id;
    private int userId;
    private int roleId;
}

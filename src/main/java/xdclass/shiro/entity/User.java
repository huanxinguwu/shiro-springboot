package xdclass.shiro.entity;

import lombok.Data;
/**
 * 用户实体类
 */
import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private int id;
    private String userName;
    private String password;
    private List<Role> roleList;
}

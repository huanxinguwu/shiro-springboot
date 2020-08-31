package xdclass.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilter( DefaultWebSecurityManager securityManager) {
        System.out.println("执行ShiroFilterFactoryBean");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果某个接口需要用户登录才能访问，那么在这里设置登录接口的路径
        //如果是前后端分离的项目，则跳转至登录接口，不是前后端分离的，跳转至登录页面
        shiroFilterFactoryBean.setLoginUrl("/public/login");
        //设置登录成功跳转的路径,（如果前后端分离的项目，则不许需要这个）
        shiroFilterFactoryBean.setSuccessUrl("/public/index");
        //设置如果是访问的未授权的接口，指定到跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/public/nopermission");
         //以下权限可以根据实际需要进行配置
        //一定要定义成 LinkedHashMap（），否则会发生一些意外情况
        Map<String,String> filterMap=new LinkedHashMap<>();
        //设定登录才可以访问的拦截
        filterMap.put("/authc/**","authc");
        filterMap.put("/logout","logout");
        //设定游客可访问的
        filterMap.put("/public/**","anon");
        //设定特定的角色，如admin，才能访问路径，如/admin
        filterMap.put("/admin/**","roles[admin]");
        //有特定权限的如edit，才能访问的路径，如
        filterMap.put("/edit/**","perms[video_add]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager( customRealm customRealm) {
        System.out.println("执行getDefaultWebSecurityManager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        //如果不是前后端分离项目，不需要设置sessionmanager
        securityManager.setSessionManager(sessionManager());
        return securityManager;

    }
    @Bean
    public customRealm getRealm(){
        System.out.println("执行getRealm");
        return new customRealm();
    }
    @Bean
    //定义密码hash散列
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //设置散列算法，比如md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //设置双重md5加密，防止暴力破解，就相当于md5之后再md5
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public SessionManager sessionManager(){
        CustomerSessionManager customerSessionManager=new CustomerSessionManager();
        //设置会话超时时间，默认30分钟，单位毫秒，这里设置为20秒
        customerSessionManager.setGlobalSessionTimeout(20000);
        return customerSessionManager;
    }
}

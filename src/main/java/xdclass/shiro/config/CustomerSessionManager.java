package xdclass.shiro.config;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 如果是单体应用，不是前后端分离项目，不用编写这个类处理session
 */
public class CustomerSessionManager extends DefaultWebSessionManager {
private static final String AUTHORIZATION="token";
public CustomerSessionManager(){
    super();
}
@Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
    //String sessionId=  WebUtils.toHttp(request);
    return null;
}


}

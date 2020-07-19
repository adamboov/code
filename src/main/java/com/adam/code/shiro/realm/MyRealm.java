package com.adam.code.shiro.realm;

import com.adam.code.entity.User;
import com.adam.code.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * @author VAIO-adam
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;

    /**
     * 授权
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 权限认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = iUserService.findByUserName(userName);
        if (user == null) {
            return null;
        } else {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getCreateDate(), user.getPassword(), "xx");
            return authenticationInfo;
        }

    }
}

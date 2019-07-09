package com.example.blog.config.shiro;

import com.example.blog.model.Menu;
import com.example.blog.model.Roles;
import com.example.blog.model.TUser;
import com.example.blog.service.MenuService;
import com.example.blog.service.RolesService;
import com.example.blog.service.TUserService;
import com.example.blog.util.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName MyRealm
 * @Author chenxue 为shiro提供用户安全信息
 * @Description
 * @Date 2019/7/4 15:30
 **/
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private TUserService tUserService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private MenuService menuService;
    //告诉shiro如何根据获取到的用户信息中的密码来校验密码
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        /*HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        //是否采用16进制，默认是true
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        //哈希值 设置迭代次数为1024。
        hashedCredentialsMatcher.setHashIterations(1024);*/
        super.setCredentialsMatcher(credentialsMatcher);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        TUser tUser = null;
        String username = "";
        if(null != principals){
            username = (String) principals.getPrimaryPrincipal();
            tUser = tUserService.getUserByUsername(StringTools.stringof(username));
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try{
            //查询用户所有的角色
            List<Roles> rolesList = rolesService.getRoleByUserId(tUser.getId());
            for (Roles roles : rolesList){
                authorizationInfo.addRole(roles.getRoleKey());
            }
            // 查询用户角色对应的权限
            List<Menu> menuList = menuService.getMenuByRoleId(tUser.getId());
            for (Menu menu : menuList){
                authorizationInfo.addStringPermission(menu.getCode());
            }

        }catch (Exception e){
            log.info("shiro 授权异常" + e.getMessage());
        }
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        TUser tUser = tUserService.getUserByUsername(username);
        if(tUser == null){
            throw new AuthenticationException("token为空!");
        }
        return new SimpleAuthenticationInfo(tUser.getUsername(),tUser.getPassword(),getName());
    }
}

package org.apache.shiro.biz.jwt.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.biz.authc.DelegateAuthenticationInfo;
import org.apache.shiro.biz.authc.token.DelegateAuthenticationToken;
import org.apache.shiro.biz.jwt.PrincipalJwtRepository;
import org.apache.shiro.biz.jwt.token.JWTAuthenticationToken;
import org.apache.shiro.biz.realm.AbstractPrincipalRealm;
import org.apache.shiro.util.ByteSource;

public class JWTPrincipalRealm extends AbstractPrincipalRealm {

	@Override
	protected DelegateAuthenticationToken createDelegateAuthenticationToken(AuthenticationToken token) {
		return (JWTAuthenticationToken) token;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfoInternal(AuthenticationToken token) {

		SimpleAccount account = null;
		if (getRepository() instanceof PrincipalJwtRepository) {

			PrincipalJwtRepository jwtRepository = (PrincipalJwtRepository) getRepository();
			JWTAuthenticationToken upToken = (JWTAuthenticationToken) token;

			// do real thing
			// new delegate authentication token and invoke doAuthc method
			DelegateAuthenticationInfo delegateAuthcInfo = getRepository()
					.getAuthenticationInfo(this.createDelegateAuthenticationToken(token));
			if (delegateAuthcInfo != null && jwtRepository.validateToken(upToken.getToken())) {
				account = new SimpleAccount(delegateAuthcInfo.getPrincipal(), delegateAuthcInfo.getCredentials(),
						ByteSource.Util.bytes(delegateAuthcInfo.getCredentialsSalt()), getName());
			}

		}
		return account;

	}

}

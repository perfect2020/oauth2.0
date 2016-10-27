
package org.oauth2.server.grant.impl;

import org.apache.commons.lang3.StringUtils;
import org.base.constants.ExecuteStatus;
import org.base.exception.RuntimeExceptionWarning;
import org.oauth2.server.data.DataHandler;
import org.oauth2.server.fetcher.clientcredential.ClientCredentialFetcher;
import org.oauth2.server.grant.GrantResult;
import org.oauth2.server.models.AuthInfo;
import org.oauth2.server.pluging.Request;

import java.beans.Transient;

/**
 *
 * authrization code 类型授权
 * 
 * @author wangwr
 *
 */
public class AuthorizationCodeGrantHandler extends AbstractGrantHandler {

	@Override
	public GrantResult handleRequest(DataHandler dataHandler) {

		Request request = dataHandler.getRequest();

		ClientCredentialFetcher.ClientCredential clientCredential = getClientCredentialFetcher().fetch(request);

		String clientId = clientCredential.getClientId();

		String code = getParameter(request, "code");
		String redirectUri = getParameter(request, "redirect_uri");

		AuthInfo authInfo = dataHandler.getAuthInfoByCode(code);

		if (authInfo == null) {
			throw new RuntimeExceptionWarning(ExecuteStatus.invalid_code);
		}
		if (!authInfo.getClientId().equals(clientId)) {
			throw new RuntimeExceptionWarning(ExecuteStatus.client_id_not_match);
		}
		if (!(StringUtils.isNotEmpty(authInfo.getRedirectUri())
		&& authInfo.getRedirectUri().equals(redirectUri))) {
			throw new RuntimeExceptionWarning(ExecuteStatus.redirect_uri_not_match);
		}

		return issueAccessToken(dataHandler, authInfo);
	}
}

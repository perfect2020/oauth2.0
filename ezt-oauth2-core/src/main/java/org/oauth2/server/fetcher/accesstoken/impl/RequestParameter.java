/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.oauth2.server.fetcher.accesstoken.impl;

import org.apache.commons.lang3.StringUtils;
import org.oauth2.server.fetcher.accesstoken.AccessTokenFetcher;
import org.oauth2.server.pluging.Request;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 获取access_token相关参数
 * @author wangwr
 *
 */
public class RequestParameter implements AccessTokenFetcher {


	@Override
	public boolean match(Request request) {
		String accessToken = request.getParameter("access_token");
		return StringUtils.isNotEmpty(accessToken);
	}

	/**
	 * Fetch an access token from a request parameter and return it.
	 * This method must be called when a result of the match() method is true
	 * only.
	 * 
	 * @param request The request object.
	 * @return the fetched access token.
	 */
	@Override
	public FetchResult fetch(Request request) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> parameterMap = request.getParameterMap();
		params.putAll(parameterMap);
		String token = params.get("access_token");
		params.remove("access_token");
		return new FetchResult(token, params);
	}

}

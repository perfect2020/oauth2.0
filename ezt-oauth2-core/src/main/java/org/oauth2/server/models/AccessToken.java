package org.oauth2.server.models;

import java.util.Date;

/**
 * @author wangwr
 *
 */
public class AccessToken {

	private Long authId;
	private String token;
	private long expiresIn;
	private Date createdOn;

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}

package org.ezt.repositories;

import org.ezt.models.OAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangwr on 2016/4/1.
 */
public interface OAuthAccessTokenRepository extends JpaRepository<OAuthAccessToken,Long> {

    OAuthAccessToken findByAccessToken(String accessToken);

}

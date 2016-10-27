package org.ezt.repositories;

import org.ezt.models.OAuthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangwr on 2016/4/1.
 */
public interface OAuthRefreshTokenRepository extends JpaRepository<OAuthRefreshToken,Long> {

    OAuthRefreshToken findByRefreshToken(String refreshToken);
}

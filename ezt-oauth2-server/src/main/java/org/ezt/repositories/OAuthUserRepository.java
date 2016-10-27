package org.ezt.repositories;

import org.ezt.models.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangwr on 2016/4/1.
 */
public interface OAuthUserRepository extends JpaRepository<OAuthUser,String> {

    OAuthUser findByUserId(Long userId);
}

package org.ezt.repositories;

import org.ezt.models.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Created by wangwr on 2016/3/31.
 */
public interface OAuthClientRepository extends JpaRepository<OAuthClient,String> {


}

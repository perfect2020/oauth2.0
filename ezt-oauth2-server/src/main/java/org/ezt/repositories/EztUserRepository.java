package org.ezt.repositories;

import org.ezt.models.EztUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangwr on 2016/4/6.
 */
public interface EztUserRepository extends JpaRepository<EztUser,Long> {

    @Query("select  e from EztUser e where e.euMobile=?1 or e.euNumber=?1")
    EztUser findByMobileOrNumber(String account);
}

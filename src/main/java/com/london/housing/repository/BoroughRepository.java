package com.london.housing.repository;

import com.london.housing.entity.Borough;
import org.springframework.stereotype.Repository;

/**
 * @author KuznetsovNE/ 20.05.2015.
 */
@Repository
public class BoroughRepository extends CommonRepository {

    public Borough saveBorough(Borough borough) {
        return save(borough);
    }
}

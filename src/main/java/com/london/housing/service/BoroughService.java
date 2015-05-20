package com.london.housing.service;

import com.london.housing.entity.Borough;
import com.london.housing.repository.BoroughRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KuznetsovNE/ 20.05.2015.
 */
@Service
public class BoroughService {

    @Autowired
    private BoroughRepository boroughRepository;

    public List<Borough> saveBoroughs(List<Borough> boroughs) {
        List<Borough> result = new ArrayList<>();
        for (Borough borough : boroughs) {
            Borough saved = boroughRepository.save(borough);
            result.add(saved);
        }
        return result;
    }
}

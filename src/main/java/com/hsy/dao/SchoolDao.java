package com.hsy.dao;

import com.hsy.domain.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolDao extends MongoRepository<School, String> {
    School findSchoolBy(String id);
}

package com.adex.assessment.codeevaluation.dao;

import com.adex.assessment.codeevaluation.entity.UserAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAgentRepository extends JpaRepository<UserAgent,String> {
    Boolean existsUserAgentByUa(String userAgent);
}

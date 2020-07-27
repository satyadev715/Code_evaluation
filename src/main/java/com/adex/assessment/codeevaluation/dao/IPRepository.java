package com.adex.assessment.codeevaluation.dao;

import com.adex.assessment.codeevaluation.entity.IPAddressBlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface IPRepository extends JpaRepository<IPAddressBlackList,Long> {

      @Query(value ="select * from ip_blacklist where INET_NTOA(ip) = ?1", nativeQuery = true)
      Optional<IPAddressBlackList> existsByIp(String ip);
}

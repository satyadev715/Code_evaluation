package com.adex.assessment.codeevaluation.dao;

import com.adex.assessment.codeevaluation.entity.RequestData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface RequestDataRepository extends CrudRepository<RequestData,Integer> {

  @Query(value ="select count(request_id) from request_data where customer_id = ?1 and date(request_time) = ?2", nativeQuery = true)
  long findAllWithCustomerIdAndRequestTime(Integer customer_id, LocalDate request_time);

}

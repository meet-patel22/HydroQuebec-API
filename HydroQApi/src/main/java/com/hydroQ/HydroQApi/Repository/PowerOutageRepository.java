package com.hydroQ.HydroQApi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hydroQ.PowerOutages.Entity.PowerOutage ;


@Repository
public interface PowerOutageRepository extends CrudRepository<PowerOutage, Long>  {

}

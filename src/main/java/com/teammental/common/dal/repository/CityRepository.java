package com.teammental.common.dal.repository;

import com.teammental.common.dal.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Short> {
}

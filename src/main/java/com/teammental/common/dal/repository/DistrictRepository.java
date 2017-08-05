package com.teammental.common.dal.repository;

import java.util.List;

import com.teammental.common.dal.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
  List<District> findAllByCityId(Integer cityId);
}

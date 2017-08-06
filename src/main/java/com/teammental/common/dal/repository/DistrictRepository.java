package com.teammental.common.dal.repository;

import com.teammental.common.dal.entity.District;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
  List<District> findAllByCityId(Integer cityId);
}

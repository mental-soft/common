package com.teammental.common.dal.repository;

import com.teammental.common.dal.entity.GenericValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericValueRepository extends JpaRepository<GenericValue, Integer> {
}

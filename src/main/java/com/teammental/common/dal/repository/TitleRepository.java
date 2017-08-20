package com.teammental.common.dal.repository;

import com.teammental.common.dal.entity.Title;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
  List<Title> findByClientId(Integer clientId);
}

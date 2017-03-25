package jpa;

import entity.District;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Coşkun on 21.1.2017.
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

  List<District> findByCity_Id(Integer cityId);

  Integer countByCity_Id(Integer cityId);

}

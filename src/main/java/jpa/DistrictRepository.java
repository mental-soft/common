package jpa;

import entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Coşkun on 21.1.2017.
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    List<District> findByCity_Id(Integer City_Id) ;

    Integer countByCity_Id(Integer CityID);

}

package jpa;

import entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nyomoto on 18.2.2017.
 */
@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer> {
}

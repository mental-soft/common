package jpa;

import entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Coşkun on 21.1.2017.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}

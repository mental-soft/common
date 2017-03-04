package jpa;

import entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nyomoto on 18.2.2017.
 */
@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
}

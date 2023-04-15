package kz.umag.hacknu.umaghacknu.repo;

import kz.umag.hacknu.umaghacknu.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

}

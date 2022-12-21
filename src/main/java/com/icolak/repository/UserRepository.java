package com.icolak.repository;

import com.icolak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
    // if we are writing any query insert, update and delete Spring needs
    // annotation for derived query @Transactional and for jpql or
    // native query @Modifying
    @Transactional
    void deleteByUserName(String username);
}

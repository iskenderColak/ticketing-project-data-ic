package com.icolak.repository;

import com.icolak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    void deleteByUserName(String username);
}

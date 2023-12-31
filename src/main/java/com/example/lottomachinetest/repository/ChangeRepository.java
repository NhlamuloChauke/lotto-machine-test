package com.example.lottomachinetest.repository;

import com.example.lottomachinetest.entity.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, Long> {
    Change findByDenomination(String denomination);
}

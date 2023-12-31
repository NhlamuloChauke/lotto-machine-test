package com.example.lottomachinetest.repository;

import com.example.lottomachinetest.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> {
    // Add custom methods or queries if needed
}

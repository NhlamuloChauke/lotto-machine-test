package com.example.lottomachinetest.repository;

import com.example.lottomachinetest.entity.LottoTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LottoTicketRepository extends JpaRepository<LottoTicket, Long> {
    LottoTicket findTopByOrderByIdDesc();
}


package com.example.lottomachinetest.service.implementation;

import com.example.lottomachinetest.exception.InvalidAmountException;
import com.example.lottomachinetest.entity.Change;
import com.example.lottomachinetest.entity.Lotto;
import com.example.lottomachinetest.entity.LottoTicket;
import com.example.lottomachinetest.service.LottoMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class LottoMachineServiceImpl implements LottoMachineService {

    @Override
    public void addFunds(BigDecimal amount) throws InvalidAmountException {

    }

    @Override
    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections) {

    }

    @Override
    public void placeRandomLottoBet(Lotto lotto) {

    }

    @Override
    public void placeQuickFiveBet(Lotto lotto, List<List<Integer>> selections) {

    }

    @Override
    public void placeRandomFiveBet(Lotto lotto) {

    }

    @Override
    public void cancelTicket() {

    }

    @Override
    public String printTicket() {
        return null;
    }

    @Override
    public void resultTicket(List<Integer> numbers) {

    }

    @Override
    public BigDecimal getBalance() {
        return null;
    }

    @Override
    public List<LottoTicket> getTickets() {
        return null;
    }

    @Override
    public List<Change> withdrawFunds() {
        return null;
    }
}

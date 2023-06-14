package com.example.lottomachinetest.service;

import com.example.lottomachinetest.model.Lotto;
import com.example.lottomachinetest.model.LottoTicket;
import com.example.lottomachinetest.model.Change;
import com.example.lottomachinetest.exception.InvalidAmountException;

import java.math.BigDecimal;
import java.util.List;


public interface LottoMachineService {

    public void addFunds(BigDecimal amount) throws InvalidAmountException;

    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections);

    public void placeRandomLottoBet(Lotto lotto);

    public void placeQuickFiveBet(Lotto lotto, List<List<Integer>> selections);

    public void placeRandomFiveBet(Lotto lotto) ;

    public void cancelTicket();

    public String printTicket();

    public void resultTicket(List<Integer> numbers);

    public BigDecimal getBalance();

    public List<LottoTicket> getTickets();

    public List<Change> withdrawFunds();
}

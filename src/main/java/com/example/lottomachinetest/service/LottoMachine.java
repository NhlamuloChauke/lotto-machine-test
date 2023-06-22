package com.example.lottomachinetest.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.lottomachinetest.entity.Lotto;
import com.example.lottomachinetest.entity.LottoTicket;
import com.example.lottomachinetest.entity.Change;
import com.example.lottomachinetest.exception.InvalidAmountException;


public interface LottoMachine {

    public void addFunds(BigDecimal amount) throws InvalidAmountException;
    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections);
    public void cancelTicket();
    public String printTicket();
    public void resultTicket(List<Integer> numbers);


    /**
    public void placeRandomLottoBet(Lotto lotto);
    public void placeQuickFiveBet(Lotto lotto, List<List<Integer>> selections);
    public void placeRandomFiveBet(Lotto lotto);
    public BigDecimal getBalance();
    public List<LottoTicket> getTickets();
    public List<Change> withdrawFunds();**/

}


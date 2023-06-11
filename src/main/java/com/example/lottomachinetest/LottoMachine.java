package com.example.lottomachinetest;

import com.example.lottomachinetest.lotto.Lotto;
import com.example.lottomachinetest.lotto.LottoTicket;
import com.example.lottomachinetest.wallet.Change;
import com.example.lottomachinetest.wallet.InvalidAmountException;

import java.math.BigDecimal;
import java.util.List;


public class LottoMachine {

    public void addFunds(BigDecimal amount) throws InvalidAmountException {

    }

    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections) {

    }

    public void placeRandomLottoBet(Lotto lotto) {

    }

    public void placeQuickFiveBet(Lotto lotto, List<List<Integer>> selections) {

    }

    public void placeRandomFiveBet(Lotto lotto) {

    }

    public void cancelTicket() {

    }

    public String printTicket() {

    }

    public void resultTicket(List<Integer> numbers) {

    }

    public BigDecimal getBalance() {

    }

    public List<LottoTicket> getTickets() {

    }

    public List<Change> withdrawFunds() {

    }
}

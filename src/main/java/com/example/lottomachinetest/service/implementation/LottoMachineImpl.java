package com.example.lottomachinetest.service.implementation;

import com.example.lottomachinetest.entity.Change;
import com.example.lottomachinetest.entity.Lotto;
import com.example.lottomachinetest.entity.LottoTicket;
import com.example.lottomachinetest.exception.InvalidAmountException;
import com.example.lottomachinetest.repository.ChangeRepository;
import com.example.lottomachinetest.repository.LottoRepository;
import com.example.lottomachinetest.repository.LottoTicketRepository;
import com.example.lottomachinetest.service.LottoMachine;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;


@Service
@Transactional
public class LottoMachineImpl implements LottoMachine {

    private final LottoTicketRepository lottoTicketRepository;
    private final ChangeRepository changeRepository;
    private final LottoRepository lottoRepository;


    public LottoMachineImpl(LottoTicketRepository lottoTicketRepository, ChangeRepository changeRepository, LottoRepository lottoRepository) {
        this.lottoTicketRepository = lottoTicketRepository;
        this.changeRepository = changeRepository;
        this.lottoRepository = lottoRepository;
    }

    @Override
    public void addFunds(BigDecimal amount) throws InvalidAmountException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Invalid amount. Amount must be greater than zero.");
        }
        Change change = changeRepository.findByDenomination("R" + amount.intValue());
        if (change != null) {
            change.setQuantity(change.getQuantity() + 1);
        } else {
            change = new Change("R" + amount.intValue(), 1);
        }
        changeRepository.save(change);
    }

    @Override
    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections) {
        lottoRepository.save(lotto); // Save the Lotto instance before creating LottoTicket

        LottoTicket ticket = new LottoTicket(lotto, BigDecimal.valueOf(5));
        ticket.setSelections(selections);
        lottoTicketRepository.save(ticket);
        subtractBalance(BigDecimal.valueOf(5));
    }

    @Override
    public void cancelTicket() {
        LottoTicket lastTicket = lottoTicketRepository.findTopByOrderByIdDesc();
        if (lastTicket != null) {
            lottoTicketRepository.delete(lastTicket);
            addBalance(lastTicket.getCost());
        }

    }

    @Override
    public String printTicket() {
        List<LottoTicket> tickets = lottoTicketRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoTicket ticket : tickets) {
            stringBuilder.append(ticket.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void resultTicket(List<Integer> numbers) {
        List<LottoTicket> tickets = lottoTicketRepository.findAll();
        for (LottoTicket ticket : tickets) {
            BigDecimal winnings = ticket.calculateWinnings(numbers);
            if (winnings.compareTo(BigDecimal.ZERO) > 0) {
                addBalance(winnings);
                ticket.setWinnings(winnings);
                lottoTicketRepository.save(ticket);
            }
        }
    }

    private void addBalance(BigDecimal amount) {
        List<Change> changes = changeRepository.findAll();
        BigDecimal remainingAmount = amount;
        for (Change change : changes) {
            BigDecimal denomination = new BigDecimal(change.getDenomination().substring(1));
            int quantity = change.getQuantity();
            int numBillsToAdd = remainingAmount.divideToIntegralValue(denomination).intValue();
            change.setQuantity(quantity + numBillsToAdd);
            changeRepository.save(change);
            remainingAmount = remainingAmount.subtract(denomination.multiply(BigDecimal.valueOf(numBillsToAdd)));
            if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
    }

    private void subtractBalance(BigDecimal amount) {
        List<Change> changes = changeRepository.findAll();
        BigDecimal remainingAmount = amount;
        for (Change change : changes) {
            BigDecimal denomination = new BigDecimal(change.getDenomination().substring(1));
            int quantity = change.getQuantity();
            int numBillsToUse = remainingAmount.divideToIntegralValue(denomination).intValue();
            int numBillsUsed = Math.min(numBillsToUse, quantity);
            BigDecimal amountUsed = denomination.multiply(BigDecimal.valueOf(numBillsUsed));
            change.setQuantity(quantity - numBillsUsed);
            changeRepository.save(change);
            remainingAmount = remainingAmount.subtract(amountUsed);
            if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
    }

}

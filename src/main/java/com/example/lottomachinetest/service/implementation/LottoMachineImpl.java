package com.example.lottomachinetest.service.implementation;

import com.example.lottomachinetest.entity.Change;
import com.example.lottomachinetest.entity.Lotto;
import com.example.lottomachinetest.entity.LottoTicket;
import com.example.lottomachinetest.exception.InsufficientFundsException;
import com.example.lottomachinetest.exception.InvalidAmountException;
import com.example.lottomachinetest.repository.ChangeRepository;
import com.example.lottomachinetest.repository.LottoRepository;
import com.example.lottomachinetest.repository.LottoTicketRepository;
import com.example.lottomachinetest.service.LottoMachine;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
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

        BigDecimal remainingAmount = amount;
        while (remainingAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal denomination = remainingAmount.min(BigDecimal.TEN);
            String denominationString = "R" + denomination.intValue();

            Change change = changeRepository.findByDenomination(denominationString);
            if (change != null) {
                change.setQuantity(change.getQuantity() + 1);
            } else {
                change = new Change(denominationString, 1);
            }
            changeRepository.save(change);

            remainingAmount = remainingAmount.subtract(denomination);
        }
    }

    @Override
    public void placeSingleLottoBet(Lotto lotto, List<Integer> selections) {
        lottoRepository.save(lotto); // Save the Lotto instance before creating LottoTicket
        BigDecimal betAmount = BigDecimal.valueOf(5);
        if (getBalance().compareTo(betAmount) >= 0) {
            LottoTicket ticket = new LottoTicket(lotto, betAmount);
            ticket.setSelections(selections);
            lottoTicketRepository.save(ticket);
            subtractBalance(betAmount);
        } else {
            throw new InsufficientFundsException("Insufficient funds to place the bet.");
        }
     }

    @Override
    public void placeRandomLottoBet(Lotto lotto) {
        lottoRepository.save(lotto); // Save the Lotto instance before creating LottoTicket
        BigDecimal betAmount = BigDecimal.valueOf(5);
        if (getBalance().compareTo(betAmount) >= 0) {
            List<Integer> selections = generateRandomSelections(lotto.getNumbersToDraw(), lotto.getTotalNumbers());
            LottoTicket ticket = new LottoTicket(lotto, betAmount);
            ticket.setSelections(selections);
            lottoTicketRepository.save(ticket);
            subtractBalance(betAmount);
        }
        else {
            throw new InsufficientFundsException("Insufficient funds to place the bet.");
        }
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

    @Override
    public BigDecimal getWinnings() {
        BigDecimal totalWinnings = BigDecimal.ZERO;
        List<LottoTicket> tickets = lottoTicketRepository.findAll();
        for (LottoTicket ticket : tickets) {
            BigDecimal ticketWinnings = ticket.getWinnings();
            if (ticketWinnings != null) {
                totalWinnings = totalWinnings.add(ticketWinnings);
            }
        }
        return totalWinnings;
    }

    @Override
    public List<LottoTicket> getTickets() {
        return lottoTicketRepository.findAll();
    }

    @Override
    public List<Change> withdrawFunds() {
        List<Change> changes = changeRepository.findAll();
        changeRepository.deleteAll();
        return changes;
    }

    @Override
    public BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        List<Change> changes = changeRepository.findAll();
        for (Change change : changes) {
            BigDecimal denomination = new BigDecimal(change.getDenomination().substring(1));
            BigDecimal totalAmount = denomination.multiply(BigDecimal.valueOf(change.getQuantity()));
            balance = balance.add(totalAmount);
        }
        return balance;
    }

    private List<Integer> generateRandomSelections(int numbersToDraw, int totalNumbers) {
        List<Integer> selections = new ArrayList<>();
        for (int i = 1; i <= numbersToDraw; i++) {
            int number = (int) (Math.random() * totalNumbers) + 1;
            selections.add(number);
        }
        return selections;
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

        // Sort the changes in descending order by denomination value
       // changes.sort(Comparator.comparing(change -> new BigDecimal(change.getDenomination().substring(1))).reversed());
        changes.sort((change1, change2) -> {
            BigDecimal denomination1 = new BigDecimal(change1.getDenomination().substring(1));
            BigDecimal denomination2 = new BigDecimal(change2.getDenomination().substring(1));
            return denomination2.compareTo(denomination1);
        });

        for (Change change : changes) {
            BigDecimal denomination = new BigDecimal(change.getDenomination().substring(1));
            int quantity = change.getQuantity();

            // Calculate the maximum number of bills to use for the current denomination
            int numBillsToUse = remainingAmount.divideToIntegralValue(denomination).intValue();

            // Calculate the actual number of bills to use based on available quantity
            int numBillsUsed = Math.min(numBillsToUse, quantity);

            // Calculate the amount to be subtracted based on the number of bills used
            BigDecimal amountUsed = denomination.multiply(BigDecimal.valueOf(numBillsUsed));

            // Update the quantity and save the change
            change.setQuantity(quantity - numBillsUsed);
            changeRepository.save(change);

            // Subtract the used amount from the remaining amount
            remainingAmount = remainingAmount.subtract(amountUsed);

            if (remainingAmount.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
    }

}

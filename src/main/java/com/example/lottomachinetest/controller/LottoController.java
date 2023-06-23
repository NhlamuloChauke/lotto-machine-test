package com.example.lottomachinetest.controller;

import com.example.lottomachinetest.dto.PlaceQuickFiveBetRequest;
import com.example.lottomachinetest.dto.PlaceSingleLottoBetRequest;
import com.example.lottomachinetest.entity.Change;
import com.example.lottomachinetest.entity.Lotto;
import com.example.lottomachinetest.entity.LottoTicket;
import com.example.lottomachinetest.exception.InsufficientFundsException;
import com.example.lottomachinetest.exception.InvalidAmountException;
import com.example.lottomachinetest.service.LottoMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/lotto")
public class LottoController {

    private final LottoMachine lottoService;

    @Autowired
    public LottoController(LottoMachine lottoService) {
        this.lottoService = lottoService;
    }

    @PostMapping("/add-funds")
    public ResponseEntity<String> addFunds(@RequestParam BigDecimal amount) {
        try {
            lottoService.addFunds(amount);
            return ResponseEntity.ok("Funds added successfully.");
        } catch (InvalidAmountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/place-single-bet")
    public void placeSingleLottoBet(@RequestBody PlaceSingleLottoBetRequest request) {
        lottoService.placeSingleLottoBet(request.getLotto(), request.getSelections());
    }

    @PostMapping("/random-bet")
    public ResponseEntity<String> placeRandomLottoBet(@RequestBody Lotto lotto) {
        try {
            lottoService.placeRandomLottoBet(lotto);
            return ResponseEntity.ok("Lotto bet placed successfully.");
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("/quick-five-bet")
    public ResponseEntity<String> placeQuickFiveBet(@RequestBody PlaceQuickFiveBetRequest request) {
        try {
            Lotto lotto = request.getLotto();
            List<List<Integer>> selections = request.getSelections();
            lottoService.placeQuickFiveBet(lotto, selections);
            return ResponseEntity.ok("Quick five bet placed successfully.");
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @DeleteMapping("/cancel-ticket")
    public void cancelTicket() {
        lottoService.cancelTicket();
    }

    @GetMapping("/print-ticket")
    public String printTicket() {
        return lottoService.printTicket();
    }

    @PostMapping("/result-ticket")
    public void resultTicket(@RequestBody List<Integer> numbers) {
        lottoService.resultTicket(numbers);
    }
    @GetMapping("/balance-winnings")
    public BigDecimal getWinnings() {
        return lottoService.getWinnings();
    }

    @GetMapping("/balance-fund")
    public BigDecimal getBalance() {
        return lottoService.getBalance();
    }

    @GetMapping("/tickets")
    public List<LottoTicket> getTickets() {
        return lottoService.getTickets();
    }

    @GetMapping("/withdraw")
    public ResponseEntity<List<Change>> withdrawFunds() {
        List<Change> changes = lottoService.withdrawFunds();
        return ResponseEntity.ok(changes);
    }

    @PostMapping("/randomFiveBet")
    public ResponseEntity<String> placeRandomFiveBet(@RequestBody Lotto lotto) {
        try {
            lottoService.placeRandomFiveBet(lotto);
            return ResponseEntity.ok("Random five bets placed successfully.");
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

}

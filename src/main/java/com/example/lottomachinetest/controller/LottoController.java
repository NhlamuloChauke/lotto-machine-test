package com.example.lottomachinetest.controller;

import com.example.lottomachinetest.dto.PlaceSingleLottoBetRequest;
import com.example.lottomachinetest.entity.Change;
import com.example.lottomachinetest.entity.LottoTicket;
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

    @GetMapping("/tickets")
    public List<LottoTicket> getTickets() {
        return lottoService.getTickets();
    }

    @GetMapping("/withdraw")
    public ResponseEntity<List<Change>> withdrawFunds() {
        List<Change> changes = lottoService.withdrawFunds();
        return ResponseEntity.ok(changes);
    }

}

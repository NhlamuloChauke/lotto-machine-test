package com.example.lottomachinetest.controller;

import com.example.lottomachinetest.entity.Lotto;
import com.example.lottomachinetest.entity.PlaceSingleLottoBetRequest;
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
}

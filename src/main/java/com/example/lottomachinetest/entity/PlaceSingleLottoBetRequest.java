package com.example.lottomachinetest.entity;

import lombok.Data;

import java.util.List;

@Data
public class PlaceSingleLottoBetRequest {
    private Lotto lotto;
    private List<Integer> selections;
}

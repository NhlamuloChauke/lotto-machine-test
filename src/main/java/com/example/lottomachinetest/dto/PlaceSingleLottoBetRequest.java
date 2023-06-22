package com.example.lottomachinetest.dto;

import com.example.lottomachinetest.entity.Lotto;
import lombok.Data;

import java.util.List;

@Data
public class PlaceSingleLottoBetRequest {
    private Lotto lotto;
    private List<Integer> selections;
}

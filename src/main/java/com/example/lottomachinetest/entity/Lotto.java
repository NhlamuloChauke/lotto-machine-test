package com.example.lottomachinetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "numbers_to_draw")
    private Integer numbersToDraw;

    @Column(name = "total_numbers")
    private Integer totalNumbers;

    public Lotto(String name, int numbersToDraw, int totalNumbers) {
        this.name = name;
        this.numbersToDraw = numbersToDraw;
        this.totalNumbers = totalNumbers;
    }
}

package com.example.lottomachinetest.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Change {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String denomination;
    @Column
    private int quantity;

    public Change(String denomination, int quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }
}

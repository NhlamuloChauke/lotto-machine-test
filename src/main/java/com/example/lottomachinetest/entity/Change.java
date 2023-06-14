package com.example.lottomachinetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "`change`")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Change {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int denomination;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

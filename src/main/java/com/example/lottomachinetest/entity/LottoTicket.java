package com.example.lottomachinetest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LottoTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lotto lotto;

   @ElementCollection
   private List<Integer> selections;

    @ElementCollection
    private List<Integer> result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

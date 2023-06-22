package com.example.lottomachinetest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class LottoTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lotto_id")
    private Lotto lotto;

    @Column(name = "ticket_cost")
    private BigDecimal cost;

    @ElementCollection
    @CollectionTable(name = "ticket_selections", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "selection")
    private List<Integer> selections;

    public LottoTicket(Lotto lotto, BigDecimal cost) {
        this.lotto = lotto;
        this.cost = cost;
    }
}

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

    @Column(name = "winnings")
    private BigDecimal winnings;

    public LottoTicket(Lotto lotto, BigDecimal cost) {
        this.lotto = lotto;
        this.cost = cost;
    }

    public BigDecimal calculateWinnings(List<Integer> numbers) {
        int matchingNumbers = 0;
        for (Integer selection : selections) {
            if (numbers.contains(selection)) {
                matchingNumbers++;
            }
        }

        BigDecimal winnings = BigDecimal.ZERO;

        // Define your rules for calculating the winnings based on the number of matching numbers
        if (matchingNumbers == 3) {
            winnings = BigDecimal.valueOf(100);
        } else if (matchingNumbers == 4) {
            winnings = BigDecimal.valueOf(1000);
        } else if (matchingNumbers == 5) {
            winnings = BigDecimal.valueOf(10000);
        } else if (matchingNumbers == 6) {
            winnings = BigDecimal.valueOf(1000000);
        }

        return winnings;
    }
}

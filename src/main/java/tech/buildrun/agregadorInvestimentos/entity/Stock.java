package tech.buildrun.agregadorInvestimentos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_stocks")
public class Stock {

    @Id
    @Column(name = "stock_id")
    private String stockId;

    @Column(name = "description")
    private String description;

    public Stock() {}

    public Stock(String stockId, String description) {
        this.stockId = stockId;
        this.description = description;
    }
}

package tech.buildrun.agregadorInvestimentos.controller;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.agregadorInvestimentos.dtos.stock.CreateStockDto;
import tech.buildrun.agregadorInvestimentos.service.StockService;

import java.net.URI;

@Getter
@RestController
@RequestMapping("/v1/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateStockDto createStockDto) {
        this.stockService.createStock(createStockDto);
        return ResponseEntity.ok().build();
    }
}

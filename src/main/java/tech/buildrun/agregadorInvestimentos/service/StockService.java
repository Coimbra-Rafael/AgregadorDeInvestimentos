package tech.buildrun.agregadorInvestimentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.buildrun.agregadorInvestimentos.dtos.stock.CreateStockDto;
import tech.buildrun.agregadorInvestimentos.entity.Stock;
import tech.buildrun.agregadorInvestimentos.repository.StockRepository;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public void createStock(CreateStockDto createStockDto) {
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        this.stockRepository.save(stock);
    }
}

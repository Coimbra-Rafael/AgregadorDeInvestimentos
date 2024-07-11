package tech.buildrun.agregadorInvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.agregadorInvestimentos.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
}
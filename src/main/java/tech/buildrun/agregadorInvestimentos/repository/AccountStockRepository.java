package tech.buildrun.agregadorInvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.agregadorInvestimentos.entity.AccountStock;
import tech.buildrun.agregadorInvestimentos.entity.AccountStockId;
@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
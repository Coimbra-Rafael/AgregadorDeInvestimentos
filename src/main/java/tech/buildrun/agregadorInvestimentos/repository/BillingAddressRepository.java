package tech.buildrun.agregadorInvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.agregadorInvestimentos.entity.BillingAddress;

import java.util.UUID;
@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
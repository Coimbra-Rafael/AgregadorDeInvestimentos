package tech.buildrun.agregadorInvestimentos.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.agregadorInvestimentos.dtos.account.AccountStockResponseDto;
import tech.buildrun.agregadorInvestimentos.dtos.account.AssociateAccountStockDto;
import tech.buildrun.agregadorInvestimentos.dtos.account.CreateAccountDto;
import tech.buildrun.agregadorInvestimentos.service.AccountService;

import java.util.List;

@Getter
@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> updateUserById(@PathVariable("accountId") String accountId, @RequestBody AssociateAccountStockDto associateAccountStockDto) {

        this.accountService.associateStock(accountId, associateAccountStockDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> updateUserById(@PathVariable("accountId") String accountId) {

        var stocks = this.accountService.listStocks(accountId);
        return ResponseEntity.ok(stocks);
    }

}

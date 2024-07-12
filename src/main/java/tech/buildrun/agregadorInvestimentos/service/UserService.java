package tech.buildrun.agregadorInvestimentos.service;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.buildrun.agregadorInvestimentos.dtos.account.AccountResponseDto;
import tech.buildrun.agregadorInvestimentos.dtos.account.CreateAccountDto;
import tech.buildrun.agregadorInvestimentos.dtos.user.CreateUserDto;
import tech.buildrun.agregadorInvestimentos.dtos.user.UpdateUserDto;
import tech.buildrun.agregadorInvestimentos.entity.Account;
import tech.buildrun.agregadorInvestimentos.entity.BillingAddress;
import tech.buildrun.agregadorInvestimentos.entity.User;
import tech.buildrun.agregadorInvestimentos.repository.AccountRepository;
import tech.buildrun.agregadorInvestimentos.repository.BillingAddressRepository;
import tech.buildrun.agregadorInvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.*;

@Getter
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final BillingAddressRepository addressRepository;
    private final BillingAddressRepository billingAddressRepository;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository addressRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        var entity = new User(UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = this.userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        var user = this.userRepository.findById(UUID.fromString(userId));
        return user;
    }

    public List<User> listUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUserById(String userId){
        var id =  UUID.fromString(userId);
        var userExist = this.userRepository.existsById(id);
        if(userExist){
         this.userRepository.deleteById(id);
        }
    }
    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id =  UUID.fromString(userId);

        var userEntity = this.userRepository.findById(id);
        if(userEntity.isPresent()){
            var user = userEntity.get();
            if(updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if(updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            this.userRepository.save(user);
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {
        var user  = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var account = new Account(UUID.randomUUID(),
                createAccountDto.description(),
                user,
                null,
                new HashSet<>()
        );

        var accountCreated = this.accountRepository.save(account);

        var billingAddress = new BillingAddress(accountCreated.getAccountId(),
                accountCreated,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);


    }

    public List<AccountResponseDto> listAccounts(String userId) {
        var user  = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts().stream().map(ac -> new AccountResponseDto(ac.getAccountId().toString(), ac.getDescription())).toList();



    }
}

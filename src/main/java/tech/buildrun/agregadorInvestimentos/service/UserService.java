package tech.buildrun.agregadorInvestimentos.service;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.buildrun.agregadorInvestimentos.dtos.user.CreateUserDto;
import tech.buildrun.agregadorInvestimentos.dtos.user.UpdateUserDto;
import tech.buildrun.agregadorInvestimentos.entity.User;
import tech.buildrun.agregadorInvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}

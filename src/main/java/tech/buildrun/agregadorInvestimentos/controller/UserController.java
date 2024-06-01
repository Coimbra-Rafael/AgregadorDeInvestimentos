package tech.buildrun.agregadorInvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.agregadorInvestimentos.entity.User;
import tech.buildrun.agregadorInvestimentos.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = this.userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/"+ userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUser(@PathVariable("userId") String userId){
        var user = this.userService.getUserById(userId);

        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listaUser(){
        var users = this.userService.listUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        this.userService.deleteUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        this.userService.updateUserById(userId, updateUserDto);

        return ResponseEntity.noContent().build();
    }
}

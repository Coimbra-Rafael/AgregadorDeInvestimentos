package tech.buildrun.agregadorInvestimentos.service;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.buildrun.agregadorInvestimentos.dtos.user.CreateUserDto;
import tech.buildrun.agregadorInvestimentos.entity.User;
import tech.buildrun.agregadorInvestimentos.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@Getter
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;;

    @InjectMocks
    private UserService userService;

    @Nested
    class createUser {
        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess() {
            // Arrange
            var user =  new User(UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null);
            doReturn(null).when(userRepository.save(any()));
            var imput = new CreateUserDto("username", "email@email.com", "password");
            // Act
            var output = userService.createUser(imput);
            // Assert
            assertNotNull(output);
        }
    }
}
package micro.service.authservice.controller;

import micro.service.authservice.entity.User;
import micro.service.authservice.entity.request.LoginRequest;
import micro.service.authservice.repository.UserRepository;
import micro.service.authservice.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    // Các thành viên Autowired khác có thể được thêm vào tại đây
    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Helllllllllllo");
    }


    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        System.out.println("CATCH");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        return jwtUtils.generateToken(loginRequest.getEmail());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserById(@PathVariable("email") String email) {
        System.out.println("CATCH");
        return ResponseEntity.ok(userRepository.findByEmail(email));
    }

    // Các phương thức hỗ trợ khác có thể được thêm vào tại đây

    private boolean isValidCredentials(String username, String password) {
        // Kiểm tra thông tin đăng nhập
        // Ví dụ:
        return username.equals("admin") && password.equals("123456");
    }
}

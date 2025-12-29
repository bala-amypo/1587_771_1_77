// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/users")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     // ✅ NEW POST METHOD (ONLY ADDITION)
//     @PostMapping
//     public ResponseEntity<User> createUser(@RequestBody User user) {
//         return ResponseEntity.ok(userService.register(user));
//     }

//     // EXISTING METHODS (DO NOT TOUCH)
//     @GetMapping("/{id}")
//     public ResponseEntity<User> getById(@PathVariable Long id) {
//         return ResponseEntity.ok(userService.getById(id));
//     }

//     @GetMapping("/instructors")
//     public ResponseEntity<List<User>> getInstructors() {
//         return ResponseEntity.ok(userService.listInstructors());
//     }
// }





package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER (PUBLIC)
    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public UserDTO register(@RequestBody User user) {
        return toDTO(userService.register(user));
    }

    // LOGIN + RETURN TOKEN (PUBLIC)
    @PostMapping("/login")
    @Operation(summary = "User login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getUsername(), request.getPassword());

        String token = jwtUtil.generateToken(user);

        return new LoginResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }

   

    private UserDTO toDTO(User u) {
        return UserDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .role(u.getRole())
                .build();
    }
}



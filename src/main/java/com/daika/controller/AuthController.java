package com.daika.controller;

import com.daika.dto.AuthRequest;
import com.daika.model.User;
import com.daika.repository.UserRepository;
import com.daika.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository ur;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager am;

    @Autowired
    private JwtService jwtService;
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
       logger.info("Logging stated hehe he");
        // encode password before saving
        user.setPassword(encoder.encode(user.getPassword()));

        User savedUser = ur.save(user);
        logger.info("Logging done and user saved..");

        return ResponseEntity.ok(savedUser);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req){

        try {

            System.out.println("Login attempt: " + req.getEmail());

            // authenticate user
            am.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );

            // generate JWT token
            String token = jwtService.generateToken(req.getEmail());

            System.out.println("Generated token: " + token);

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
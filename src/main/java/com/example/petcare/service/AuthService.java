package com.example.petcare.service;

import com.example.petcare.dto.AuthRequest;
import com.example.petcare.dto.AuthResponse;
import com.example.petcare.dto.RegisterRequest;
import com.example.petcare.dto.UserInfoResponse;
import com.example.petcare.model.Owner;
import com.example.petcare.model.OwnerDetails;
import com.example.petcare.repository.OwnerRepository;
import com.example.petcare.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void registerOwner(RegisterRequest request) {
        if (ownerRepository.existsByLogin(request.login())) {
            throw new RuntimeException("Login jest już zajęty!");
        }

        OwnerDetails details = new OwnerDetails();
        details.setName(request.name());
        details.setSurname(request.surname());

        Owner owner = new Owner();
        owner.setLogin(request.login());
        owner.setPassword(passwordEncoder.encode(request.password()));
        owner.setDetails(details);

        ownerRepository.save(owner);
    }

    public AuthResponse login(AuthRequest request) {
        Owner owner = ownerRepository.findByLogin(request.login())
                .orElseThrow(() -> new RuntimeException("Podano błędne dane logowania"));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.login(),
                            request.password()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Podano błędne dane logowania");
        }

        var userDetails = new org.springframework.security.core.userdetails.User(
                owner.getLogin(),
                owner.getPassword(),
                java.util.Collections.emptyList()
        );

        String jwtToken = jwtService.generateToken(userDetails);

        return new AuthResponse(jwtToken);
    }

    public UserInfoResponse getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        Owner owner = ownerRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono zalogowanego użytkownika"));

        return new UserInfoResponse(
                owner.getLogin(),
                owner.getDetails().getName(),
                owner.getDetails().getSurname(),
                "OWNER"
        );
    }
}
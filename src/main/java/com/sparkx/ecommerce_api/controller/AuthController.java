package com.sparkx.ecommerce_api.controller;

import com.sparkx.ecommerce_api.model.dto.request.LoginRequest;
import com.sparkx.ecommerce_api.model.dto.request.RegisterRequest;
import com.sparkx.ecommerce_api.model.dto.response.AuthResponse;
import com.sparkx.ecommerce_api.service.inf.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authentication",
        description = "Endpoints for user registration and login"
)
public class AuthController {

    private final IAuthService authService;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account and stores credentials securely"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User registered successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "User registered successfully")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed / Invalid input",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        String response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Login user",
            description = "Authenticates user credentials and returns a JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class),
                            examples = @ExampleObject(
                                    value = "{ \"token\": \"eyJhbGciOiJIUzI1NiJ9...\" }"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid username or password",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

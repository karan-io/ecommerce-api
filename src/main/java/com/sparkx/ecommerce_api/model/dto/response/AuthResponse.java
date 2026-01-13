package com.sparkx.ecommerce_api.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Authentication response containing JWT token")
public class AuthResponse {

    @Schema(
            description = "JWT access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIn0.signature"
    )
    private String token;
}

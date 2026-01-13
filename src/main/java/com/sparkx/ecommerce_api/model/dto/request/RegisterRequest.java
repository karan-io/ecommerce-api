package com.sparkx.ecommerce_api.model.dto.request;

import com.sparkx.ecommerce_api.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "First Name is required!")
    @Size(min = 4,max = 40,message = "First should be between 4 to 40 characters")
    private String firstName;

    @NotNull(message = "last Name is required!")
    @Size(min = 4,max = 40,message = "Last should be between 4 to 40 characters")
    private String lastName;

    @Email(message = "Valid Email is required!")
    @NotNull(message = "Email is required!")
    private String email;

    @NotNull(message = "Password is required!")
    @Size(min = 6,message = "Password should be atleast 6 characters!")
    private String password;

    @NotNull(message = "Role is required!")
    private UserRole role;
}

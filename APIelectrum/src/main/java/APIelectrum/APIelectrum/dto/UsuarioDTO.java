package APIelectrum.APIelectrum.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(@NotBlank String username,
                         @NotBlank String email,
                         @NotBlank String password) {
}

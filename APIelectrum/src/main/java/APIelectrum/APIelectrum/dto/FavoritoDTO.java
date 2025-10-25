package APIelectrum.APIelectrum.dto;

import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.module.Usuario;
import jakarta.validation.constraints.NotBlank;

public record FavoritoDTO(@NotBlank Usuario usuario,
                          @NotBlank Produto produto) {
}

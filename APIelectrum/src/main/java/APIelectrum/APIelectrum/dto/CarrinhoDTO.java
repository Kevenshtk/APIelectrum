package APIelectrum.APIelectrum.dto;

import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.module.Usuario;
import jakarta.validation.constraints.NotBlank;

public record CarrinhoDTO(@NotBlank Usuario usuario, @NotBlank Produto produto) {
}

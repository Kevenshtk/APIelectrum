package APIelectrum.APIelectrum.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO(@NotBlank String name,
                         @NotBlank String category,
                         @NotBlank String description,
                         @NotBlank double price,
                         @NotBlank String image,
                         @NotBlank String tag) {
}

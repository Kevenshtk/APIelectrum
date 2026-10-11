package APIelectrum.APIelectrum.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(@NotBlank @Size(max = 100) String name,
                             @NotBlank @Size(max = 50) String category,
                             @NotBlank @Size(max = 250) String description,
                             @NotBlank @Positive Double price,
                             @NotBlank String image,
                             @NotBlank String tag) {
}

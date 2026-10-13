package APIelectrum.APIelectrum.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CarrinhoRequest(@NotNull @Valid ReferenciaRequest usuario,
                              @NotNull @Valid ReferenciaRequest produto) {
}

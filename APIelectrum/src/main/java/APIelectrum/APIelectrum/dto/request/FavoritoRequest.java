package APIelectrum.APIelectrum.dto.request;

import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.module.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FavoritoRequest(@NotNull @Valid ReferenciaRequest usuario,
                              @NotNull @Valid ReferenciaRequest produto) {
}

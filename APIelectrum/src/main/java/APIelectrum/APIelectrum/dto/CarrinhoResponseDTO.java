package APIelectrum.APIelectrum.dto;

import APIelectrum.APIelectrum.module.Produto;

public record CarrinhoResponseDTO(Produto produto,
                                  Integer quantidade) {
}

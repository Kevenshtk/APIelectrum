package APIelectrum.APIelectrum.dto.response;

import APIelectrum.APIelectrum.module.Carrinho;

public record CarrinhoItemResponse(ProdutoResponse produto,
                                   Integer quantidade) {
    public static CarrinhoItemResponse from(Carrinho carrinho){
        return new CarrinhoItemResponse(
                ProdutoResponse.from(carrinho.getProduto()),
                carrinho.getQuantidade());
    }
}

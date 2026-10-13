package APIelectrum.APIelectrum.dto.response;

import APIelectrum.APIelectrum.module.Produto;

public record ProdutoResponse(Integer id,
                              String name,
                              String category,
                              String description,
                              double price,
                              String image,
                              String tag){

public static ProdutoResponse from(Produto produto) {
    return new ProdutoResponse(
            produto.getId(),
            produto.getName(),
            produto.getCategory(),
            produto.getDescription(),
            produto.getPrice(),
            produto.getImage(),
            produto.getTag()
    );
}
}

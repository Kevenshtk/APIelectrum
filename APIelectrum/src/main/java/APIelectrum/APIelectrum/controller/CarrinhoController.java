package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.CarrinhoDTO;
import APIelectrum.APIelectrum.dto.CarrinhoResponseDTO;
import APIelectrum.APIelectrum.dto.CarrinhoTotalDTO;
import APIelectrum.APIelectrum.module.Carrinho;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.repository.CarrinhoRepository;
import APIelectrum.APIelectrum.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:8080",
        "https://electrum-eta.vercel.app",
})
@RequestMapping("/shopping-cart")
public class CarrinhoController {
    @Autowired
    CarrinhoRepository repositoryCarrinho;
    @Autowired
    private ProdutoRepository repositoryProduto;

    @PostMapping()
    public ResponseEntity<Produto> addCarrinho(@RequestBody CarrinhoDTO carrinhoDTO){
        Integer usuarioId = carrinhoDTO.usuario().getId();
        Integer produtoId = carrinhoDTO.produto().getId();

        Optional<Carrinho> itemExistente =
                repositoryCarrinho.findByUsuarioIdAndProdutoId(usuarioId, produtoId);

        if (itemExistente.isPresent()) {
            Carrinho carrinho = itemExistente.get();
            carrinho.setQuantidade(carrinho.getQuantidade() + 1);
            repositoryCarrinho.save(carrinho);
        } else {
            Carrinho novoCarrinho = new Carrinho();
            novoCarrinho.setUsuario(carrinhoDTO.usuario());
            novoCarrinho.setProduto(carrinhoDTO.produto());
            novoCarrinho.setQuantidade(1);
            repositoryCarrinho.save(novoCarrinho);
        }

        Produto produto = repositoryProduto
                .findById(produtoId)
                .orElseThrow();

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<List<CarrinhoResponseDTO>> getProdutosCarrinho(@PathVariable(value = "idUsuario") Integer idUsuario){
        List<Carrinho> carrinho = repositoryCarrinho.findByUsuarioId(idUsuario);
        List<CarrinhoResponseDTO> response = carrinho.stream()
                .map(item -> new CarrinhoResponseDTO(item.getProduto(),
                        item.getQuantidade()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{idUsuario}/total")
    public ResponseEntity<CarrinhoTotalDTO> getTotalCarrinho(
            @PathVariable Integer idUsuario
    ) {
        List<Carrinho> carrinho = repositoryCarrinho.findByUsuarioId(idUsuario);

        BigDecimal total = carrinho.stream()
                .map(item ->
                        BigDecimal.valueOf(item.getProduto().getPrice())
                                .multiply(BigDecimal.valueOf(item.getQuantidade()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseEntity.ok(new CarrinhoTotalDTO(total));
    }

    @Transactional
    @DeleteMapping("user/{idUsuario}/product/{idProduto}")
    public ResponseEntity<Object> removeCarrinho(@PathVariable(value = "idUsuario") Integer idUsuario,
                                                 @PathVariable(value = "idProduto") Integer idProduto){

        repositoryCarrinho.deleteByUsuarioIdAndProdutoId(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{idUsuario}/product/{idProduto}/increment")
    public ResponseEntity<Void> incrementarQuantidade(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idProduto
    ) {
        Carrinho carrinho = repositoryCarrinho
                .findByUsuarioIdAndProdutoId(idUsuario, idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no carrinho"));

        carrinho.setQuantidade(carrinho.getQuantidade() + 1);
        repositoryCarrinho.save(carrinho);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{idUsuario}/product/{idProduto}/decrement")
    public ResponseEntity<Void> decrementarQuantidade(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idProduto
    ) {
        Carrinho carrinho = repositoryCarrinho
                .findByUsuarioIdAndProdutoId(idUsuario, idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no carrinho"));

        if (carrinho.getQuantidade() > 1) {
            carrinho.setQuantidade(carrinho.getQuantidade() - 1);
            repositoryCarrinho.save(carrinho);
            return ResponseEntity.noContent().build();
        } else {
            repositoryCarrinho.delete(carrinho);
            return ResponseEntity.ok().build();
        }


    }

}

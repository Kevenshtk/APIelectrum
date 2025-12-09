package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.CarrinhoDTO;
import APIelectrum.APIelectrum.module.Carrinho;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.repository.CarrinhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping()
    public ResponseEntity<Carrinho> addCarrinho(@RequestBody CarrinhoDTO carrinhoDTO){
        var carrinhoModelo = new Carrinho();

        BeanUtils.copyProperties(carrinhoDTO, carrinhoModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryCarrinho.save(carrinhoModelo));
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<List<Produto>> getProdutosCarrinho(@PathVariable(value = "idUsuario") Integer idUsuario){
        List<Carrinho> carrinho = repositoryCarrinho.findByUsuarioId(idUsuario);
        List<Produto> produtos = carrinho.stream()
                .map(Carrinho::getProduto)
                .toList();

        return ResponseEntity.ok(produtos);
    }

    @Transactional
    @DeleteMapping("user/{idUsuario}/product/{idProduto}")
    public ResponseEntity<Object> removeCarrinho(@PathVariable(value = "idUsuario") Integer idUsuario,
                                                 @PathVariable(value = "idProduto") Integer idProduto){

        repositoryCarrinho.deleteByUsuarioIdAndProdutoId(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }
}

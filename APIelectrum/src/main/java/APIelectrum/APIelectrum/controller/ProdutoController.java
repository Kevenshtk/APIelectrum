package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.ProdutoDTO;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProdutoController {
    @Autowired
    ProdutoRepository repositoryProduto;

    @PostMapping()
    public ResponseEntity<Produto> salvarProduto(@RequestBody ProdutoDTO produtoDTO){
        var produtoModelo = new Produto();

        BeanUtils.copyProperties(produtoDTO, produtoModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryProduto.save(produtoModelo));
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> getAllProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositoryProduto.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value="id") Integer id, @RequestBody ProdutoDTO produtoDTO){
        Optional<Produto> produto = repositoryProduto.findById(id);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto "+id+" não encontrado.");
        }

        BeanUtils.copyProperties(produtoDTO, produto.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryProduto.save(produto.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value="id") Integer id){
        Optional<Produto> produto = repositoryProduto.findById(id);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto "+id+" não encontrado.");
        }

        repositoryProduto.delete(produto.get());

        return ResponseEntity.status(HttpStatus.OK).body("Porduto "+id+" removido com sucesso.");
    }
}

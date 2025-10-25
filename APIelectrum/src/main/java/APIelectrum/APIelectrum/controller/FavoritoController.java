package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.FavoritoDTO;
import APIelectrum.APIelectrum.module.Favorito;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.repository.FavoritoRepository;
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
        "https://electrum-eta.vercel.app",
        "https://electrum-7ek8m1phq-kevenshtks-projects.vercel.app"
})
@RequestMapping("/favorites")
public class FavoritoController {
    @Autowired
    FavoritoRepository repositoryFavorito;

    @PostMapping()
    public ResponseEntity<Favorito> addFavorito(@RequestBody FavoritoDTO favorioDTO){
        var favoritoModelo = new Favorito();
        BeanUtils.copyProperties(favorioDTO, favoritoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(repositoryFavorito.save(favoritoModelo));
    }

    @GetMapping("user/{idUsuario}")
    public ResponseEntity<List<Produto>> getProdutosFavoritos(@PathVariable(value = "idUsuario") Integer idUsuario){
        List<Favorito> favoritos = repositoryFavorito.findByUsuarioId(idUsuario);
        List<Produto> produtos = favoritos.stream()
                .map(Favorito::getProduto)
                .toList();
        return ResponseEntity.ok(produtos);
    }

    @Transactional
    @DeleteMapping("user/{idUsuario}/product/{idProduto}")
    public ResponseEntity<Object> removeFavorito(@PathVariable(value = "idUsuario") Integer idUsuario,
                                                 @PathVariable(value = "idProduto") Integer idProduto){

        repositoryFavorito.deleteByUsuarioIdAndProdutoId(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }

}

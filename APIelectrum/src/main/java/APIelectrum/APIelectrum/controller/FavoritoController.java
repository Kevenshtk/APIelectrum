package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.request.FavoritoRequest;
import APIelectrum.APIelectrum.dto.response.ProdutoResponse;
import APIelectrum.APIelectrum.service.FavoritoService;
import jakarta.validation.Valid;
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
@RequestMapping("/favorites")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> addFavorito(@RequestBody @Valid FavoritoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.adicionar(request));
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<List<ProdutoResponse>> getProdutosFavoritos(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(favoritoService.listarPorUsuario(idUsuario));
    }

    @DeleteMapping("/user/{idUsuario}/product/{idProduto}")
    public ResponseEntity<Void> removeFavorito(@PathVariable Integer idUsuario,
                                               @PathVariable Integer idProduto) {
        favoritoService.remover(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }
}
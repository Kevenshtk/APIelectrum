package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.request.CarrinhoRequest;
import APIelectrum.APIelectrum.dto.response.CarrinhoItemResponse;
import APIelectrum.APIelectrum.dto.response.CarrinhoTotalResponse;
import APIelectrum.APIelectrum.dto.response.ProdutoResponse;
import APIelectrum.APIelectrum.service.CarrinhoService;
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
@RequestMapping("/shopping-cart")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> addCarrinho(@RequestBody @Valid CarrinhoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.adicionar(request));
    }

    @GetMapping("/user/{idUsuario}")
    public ResponseEntity<List<CarrinhoItemResponse>> getProdutosCarrinho(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(carrinhoService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/user/{idUsuario}/total")
    public ResponseEntity<CarrinhoTotalResponse> getTotalCarrinho(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(carrinhoService.calcularTotal(idUsuario));
    }

    @DeleteMapping("/user/{idUsuario}/product/{idProduto}")
    public ResponseEntity<Void> removeCarrinho(@PathVariable Integer idUsuario,
                                               @PathVariable Integer idProduto) {
        carrinhoService.remover(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{idUsuario}/product/{idProduto}/increment")
    public ResponseEntity<Void> incrementarQuantidade(@PathVariable Integer idUsuario,
                                                      @PathVariable Integer idProduto) {
        carrinhoService.incrementarQuantidade(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{idUsuario}/product/{idProduto}/decrement")
    public ResponseEntity<Void> decrementarQuantidade(@PathVariable Integer idUsuario,
                                                      @PathVariable Integer idProduto) {
        carrinhoService.decrementarQuantidade(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }
}

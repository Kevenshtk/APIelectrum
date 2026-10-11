package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.request.UsuarioRequest;
import APIelectrum.APIelectrum.dto.response.UsuarioResponse;
import APIelectrum.APIelectrum.service.UsuarioService;
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
        "https://electrum-amiiv8aht-kevenshtks-projects.vercel.app"
})
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvarUsuario(@RequestBody @Valid UsuarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.listar());
    }
}

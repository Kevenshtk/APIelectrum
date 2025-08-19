package APIelectrum.APIelectrum.controller;

import APIelectrum.APIelectrum.dto.UsuarioDTO;
import APIelectrum.APIelectrum.module.Usuario;
import APIelectrum.APIelectrum.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://electrum-eta.vercel.app"
})
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping()
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        var usuarioModelo = new Usuario();

        BeanUtils.copyProperties(usuarioDTO, usuarioModelo);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModelo));
    }

    @GetMapping()
    public  ResponseEntity<List<Usuario>> getAllUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }
}

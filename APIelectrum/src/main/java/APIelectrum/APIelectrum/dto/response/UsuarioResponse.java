package APIelectrum.APIelectrum.dto.response;

import APIelectrum.APIelectrum.module.Usuario;

public record UsuarioResponse(int id,
                              String username,
                              String email,
                              String password) {

    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getPassword());
    }

}

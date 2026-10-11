package APIelectrum.APIelectrum.service;

import APIelectrum.APIelectrum.dto.request.FavoritoRequest;
import APIelectrum.APIelectrum.dto.response.ProdutoResponse;
import APIelectrum.APIelectrum.exception.RecursoNaoEncontradoException;
import APIelectrum.APIelectrum.module.Favorito;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.module.Usuario;
import APIelectrum.APIelectrum.repository.FavoritoRepository;
import APIelectrum.APIelectrum.repository.ProdutoRepository;
import APIelectrum.APIelectrum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public FavoritoService(FavoritoRepository favoritoRepository,
                           ProdutoRepository produtoRepository,
                           UsuarioRepository usuarioRepository) {
        this.favoritoRepository = favoritoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ProdutoResponse adicionar(FavoritoRequest request) {
        Usuario usuario = buscarUsuario(request.usuario().id());
        Produto produto = buscarProduto(request.produto().id());

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProduto(produto);
        favoritoRepository.save(favorito);

        return ProdutoResponse.from(produto);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponse> listarPorUsuario(Integer idUsuario) {
        return favoritoRepository.findByUsuarioId(idUsuario).stream()
                .map(Favorito::getProduto)
                .map(ProdutoResponse::from)
                .toList();
    }

    @Transactional
    public void remover(Integer idUsuario, Integer idProduto) {
        favoritoRepository.deleteByUsuarioIdAndProdutoId(idUsuario, idProduto);
    }

    private Usuario buscarUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário " + id + " não encontrado."));
    }

    private Produto buscarProduto(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto " + id + " não encontrado."));
    }


}

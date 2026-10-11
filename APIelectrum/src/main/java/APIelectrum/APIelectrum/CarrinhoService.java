package APIelectrum.APIelectrum;

import APIelectrum.APIelectrum.dto.request.CarrinhoRequest;
import APIelectrum.APIelectrum.dto.response.ProdutoResponse;
import APIelectrum.APIelectrum.exception.RecursoNaoEncontradoException;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.repository.CarrinhoRepository;
import APIelectrum.APIelectrum.repository.ProdutoRepository;
import APIelectrum.APIelectrum.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /** Adiciona o produto ao carrinho; se já existir, soma 1 à quantidade. */
    @Transactional
    public ProdutoResponse adicionar(CarrinhoRequest request){
        Integer idUsuario = request.usuario().id();
        Integer idProduto = request.produto().id();

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow() -> new RecursoNaoEncontradoException("Produto "+ idProduto + " não encontrado.");
    }









}

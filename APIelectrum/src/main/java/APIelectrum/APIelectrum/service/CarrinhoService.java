package APIelectrum.APIelectrum.service;

import APIelectrum.APIelectrum.dto.request.CarrinhoRequest;
import APIelectrum.APIelectrum.dto.response.CarrinhoItemResponse;
import APIelectrum.APIelectrum.dto.response.CarrinhoTotalResponse;
import APIelectrum.APIelectrum.dto.response.ProdutoResponse;
import APIelectrum.APIelectrum.exception.RecursoNaoEncontradoException;
import APIelectrum.APIelectrum.module.Carrinho;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.module.Usuario;
import APIelectrum.APIelectrum.repository.CarrinhoRepository;
import APIelectrum.APIelectrum.repository.ProdutoRepository;
import APIelectrum.APIelectrum.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           ProdutoRepository produtoRepository,
                           UsuarioRepository usuarioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /** Adiciona o produto ao carrinho; se já existir, soma 1 à quantidade. */
    @Transactional
    public ProdutoResponse adicionar(CarrinhoRequest request) {
        Integer idUsuario = request.usuario().id();
        Integer idProduto = request.produto().id();

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto " + idProduto + " não encontrado."));

        Carrinho item = carrinhoRepository.findByUsuarioIdAndProdutoId(idUsuario, idProduto)
                .orElse(null);

        if (item != null) {
            item.setQuantidade(item.getQuantidade() + 1);
        } else {
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário " + idUsuario + " não encontrado."));

            item = new Carrinho();
            item.setUsuario(usuario);
            item.setProduto(produto);
            item.setQuantidade(1);
        }

        carrinhoRepository.save(item);
        return ProdutoResponse.from(produto);
    }

    @Transactional(readOnly = true)
    public List<CarrinhoItemResponse> listarPorUsuario(Integer idUsuario) {
        return carrinhoRepository.findByUsuarioId(idUsuario).stream()
                .map(CarrinhoItemResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public CarrinhoTotalResponse calcularTotal(Integer idUsuario) {
        BigDecimal total = carrinhoRepository.findByUsuarioId(idUsuario).stream()
                .map(item -> BigDecimal.valueOf(item.getProduto().getPrice())
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarrinhoTotalResponse(total);
    }

    @Transactional
    public void remover(Integer idUsuario, Integer idProduto) {
        carrinhoRepository.deleteByUsuarioIdAndProdutoId(idUsuario, idProduto);
    }

    @Transactional
    public void incrementarQuantidade(Integer idUsuario, Integer idProduto) {
        Carrinho item = buscarItem(idUsuario, idProduto);
        item.setQuantidade(item.getQuantidade() + 1);
        carrinhoRepository.save(item);
    }

    /** Diminui 1 da quantidade; se chegar a zero, remove o item do carrinho. */
    @Transactional
    public void decrementarQuantidade(Integer idUsuario, Integer idProduto) {
        Carrinho item = buscarItem(idUsuario, idProduto);

        if (item.getQuantidade() > 1) {
            item.setQuantidade(item.getQuantidade() - 1);
            carrinhoRepository.save(item);
        } else {
            carrinhoRepository.delete(item);
        }
    }

    private Carrinho buscarItem(Integer idUsuario, Integer idProduto) {
        return carrinhoRepository.findByUsuarioIdAndProdutoId(idUsuario, idProduto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado no carrinho."));
    }


}

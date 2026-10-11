package APIelectrum.APIelectrum.service;

import APIelectrum.APIelectrum.dto.request.ProdutoRequest;
import APIelectrum.APIelectrum.dto.response.ProdutoResponse;
import APIelectrum.APIelectrum.exception.RecursoNaoEncontradoException;
import APIelectrum.APIelectrum.module.Produto;
import APIelectrum.APIelectrum.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @org.springframework.transaction.annotation.Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = new Produto();
        preencher(produto, request);
        return ProdutoResponse.from(produtoRepository.save(produto));
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<ProdutoResponse> listar() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponse::from)
                .toList();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Integer id) {
        return ProdutoResponse.from(buscarEntidade(id));
    }

    @org.springframework.transaction.annotation.Transactional
    public ProdutoResponse atualizar(Integer id, ProdutoRequest request) {
        Produto produto = buscarEntidade(id);
        preencher(produto, request);
        return ProdutoResponse.from(produtoRepository.save(produto));
    }

    @Transactional
    public void deletar(Integer id) {
        produtoRepository.delete(buscarEntidade(id));
    }

    private Produto buscarEntidade(Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto " + id + " não encontrado."));
    }

    private void preencher(Produto produto, ProdutoRequest request) {
        produto.setName(request.name());
        produto.setCategory(request.category());
        produto.setDescription(request.description());
        produto.setPrice(request.price());
        produto.setImage(request.image());
        produto.setTag(request.tag());
    }


}

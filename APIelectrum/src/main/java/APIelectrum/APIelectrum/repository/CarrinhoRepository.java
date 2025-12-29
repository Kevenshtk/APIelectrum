package APIelectrum.APIelectrum.repository;

import APIelectrum.APIelectrum.module.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    List<Carrinho> findByUsuarioId(Integer usuarioId);

    void deleteByUsuarioIdAndProdutoId(Integer usuarioId, Integer produtoId);

    Optional <Carrinho>findByUsuarioIdAndProdutoId(Integer usuarioId, Integer produtoId);
}

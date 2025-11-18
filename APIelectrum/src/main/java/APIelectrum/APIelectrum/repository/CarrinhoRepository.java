package APIelectrum.APIelectrum.repository;

import APIelectrum.APIelectrum.module.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    List<Carrinho> findByUsuarioId(Integer usuarioId);

    void deleteByUsuarioIdAndProdutoId(Integer usuarioId, Integer produtoId);
}

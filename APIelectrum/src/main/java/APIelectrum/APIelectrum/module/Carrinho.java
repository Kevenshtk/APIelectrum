package APIelectrum.APIelectrum.module;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "carrinho")
public class Carrinho implements Serializable {
    private static final long serialVersion = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer quantidade;

    public Carrinho() {
    }

    public Carrinho(Integer id, Usuario usuario, Produto produto) {
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

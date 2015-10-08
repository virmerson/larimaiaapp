package br.ucdb.larimaiaapp.model;

/**
 * Created by Ronie Von on 30/09/2015.
 */
public class ItemPedido {

    private Produto produto;
    private Pedido pedido;
    private Integer quantidade;
    private Double valor;
    private Integer id;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

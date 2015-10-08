package br.ucdb.larimaiaapp.model;

/**
 * Created by williamluciodonascimento on 30/09/15.
 */
public class TipoEvento {

    private Long idTipoEvento;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(Long idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }
}

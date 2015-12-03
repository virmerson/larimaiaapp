package br.ucdb.larimaiaapp.model;

import java.io.Serializable;

/**
 * Created by Kennedy on 02/12/2015.
 */
public class Cerimonial implements Serializable {

    public int id;
    public String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao ;
    }
}

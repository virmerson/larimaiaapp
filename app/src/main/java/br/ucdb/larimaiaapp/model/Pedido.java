package br.ucdb.larimaiaapp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo on 07/10/2015.
 */
public class Pedido {
    private String indicacao, obs, localContrato, hora, rua, bairro, cep, cidade, estado, complemento, cerimonial;
    private int numero, id;
    private Date dataEvento, dataPedido;
    private Cliente cliente;
    private TipoEvento evento;


    public List<ItemPedido> listaItemPedido;

    public String getCerimonial() {
        return cerimonial;
    }

    public void setCerimonial(String cer) {
        cerimonial = cer;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getLocalContrato() {
        return localContrato;
    }

    public void setLocalContrato(String localContrato) {
        this.localContrato = localContrato;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public TipoEvento getEvento() {
        return evento;
    }

    public void setEvento(TipoEvento evento) {
        this.evento = evento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getListaItemPedido() {
        return listaItemPedido;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.listaItemPedido = listaItemPedido;
    }

}
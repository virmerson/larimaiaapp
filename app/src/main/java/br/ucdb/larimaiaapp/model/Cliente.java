package br.ucdb.larimaiaapp.model;

import java.io.Serializable;

/**
 * Created by Mar_Ju on 23/09/2015.
 */
public class Cliente implements Serializable {
        private int id;
        private String nome;
        private String email;
        private String telefone;

        public int getId() { return id; }

        public void setId(int id) {this.id = id; }

        public String getEmail() {  return email; }
        public void setEmail(String email) {this.email = email; }

        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }

        public String getNome() {return nome; }
        public void setNome(String nome) {this.nome = nome; }
    }

package com.example.administrador.teste.model.entities;

/**
 * Created by CaioZanon on 27/07/2015.
 */
public class ClientAddress {

   private String cep;
   private String tipoDeLogradouro;
   private String logradouro;
   private String bairro;
   private String cidade;
   private String estado;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientAddress address = (ClientAddress) o;

        if (cep != null ? !cep.equals(address.cep) : address.cep != null) return false;
        if (tipoDeLogradouro != null ? !tipoDeLogradouro.equals(address.tipoDeLogradouro) : address.tipoDeLogradouro != null)
            return false;
        if (logradouro != null ? !logradouro.equals(address.logradouro) : address.logradouro != null)
            return false;
        if (bairro != null ? !bairro.equals(address.bairro) : address.bairro != null) return false;
        if (cidade != null ? !cidade.equals(address.cidade) : address.cidade != null) return false;
        return !(estado != null ? !estado.equals(address.estado) : address.estado != null);

    }

    @Override
    public int hashCode() {
        int result = cep != null ? cep.hashCode() : 0;
        result = 31 * result + (tipoDeLogradouro != null ? tipoDeLogradouro.hashCode() : 0);
        result = 31 * result + (logradouro != null ? logradouro.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoDeLogradouro() {
        return tipoDeLogradouro;
    }

    public void setTipoDeLogradouro(String tipoDeLogradouro) {
        this.tipoDeLogradouro = tipoDeLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
}

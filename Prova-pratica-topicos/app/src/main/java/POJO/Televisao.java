package POJO;
import java.io.Serializable;

/**
 * Created by alexandra on 28/06/17.
 */

public class Televisao implements Serializable {
    private int id;
    private String marca, modelo, peso, resolucao;
    private boolean componenteCap, componenteDig, componenteSap;

    public Televisao() {
    }

    public Televisao(int id, String marca, String modelo, String peso, boolean componenteCap, boolean componenteDig, boolean componenteSap, String resolucao) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.componenteCap = componenteCap;
        this.componenteDig = componenteDig;
        this.componenteSap = componenteSap;
        this.resolucao = resolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public boolean isComponenteCap() {
        return componenteCap;
    }

    public void setComponenteCap(boolean componenteCap) {
        this.componenteCap = componenteCap;
    }

    public boolean isComponenteDig() {
        return componenteDig;
    }

    public void setComponenteDig(boolean componenteDig) {
        this.componenteDig = componenteDig;
    }

    public boolean isComponenteSap() {
        return componenteSap;
    }

    public void setComponenteSap(boolean componenteSap) {
        this.componenteSap = componenteSap;
    }

    @Override
    public String toString() {
        return "Marca: " + marca+". Modelo: " + modelo+". Peso: " + peso+". Resolução: " + resolucao+
                (this.componenteCap ? ". Closed Caption. ": ". Sem Closed Caption. ")+
                (this.componenteDig ? "Conversor Digital" : "Sem Conversor Digital. ")+
                (this.componenteSap ? "Func. SAP. " : "Sem Func. SAP. ");
    }
}
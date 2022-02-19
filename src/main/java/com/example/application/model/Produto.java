package com.example.application.model;

// @author Henrique Andrew da Silva

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String descricao = "";   
    private ProdutoClassificacao classif;
    private String unid_medida = "";
    private double valor_unit = 0.0;
    
    public Produto (){
        
    }
    
    public Produto (String descricao, String classif, String unid_medida, double valor_unit){        
        this.descricao = descricao;        
        this.classif = ProdutoClassificacao.Outros;
        this.unid_medida = unid_medida;
        this.valor_unit = valor_unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public ProdutoClassificacao getClassif() {
        return classif;
    }

    public void setClassif(ProdutoClassificacao classif) {
        this.classif = classif;
    }

    public String getUnid_medida() {
        return unid_medida;
    }

    public void setUnid_medida(String unid_medida) {
        this.unid_medida = unid_medida;
    }

    public double getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(double valor_unit) {
        this.valor_unit = valor_unit;
    }
    
    public boolean isPersisted(){
        return id != null;
    }  
    
    @Override
    public String toString(){
        return descricao;
    }

}

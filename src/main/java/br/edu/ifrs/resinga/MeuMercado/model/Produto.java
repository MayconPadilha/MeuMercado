package br.edu.ifrs.resinga.MeuMercado.model;

import java.math.BigDecimal;

import br.edu.ifrs.resinga.MeuMercado.layout.controllers.dto.DadosCadastroProduto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class Produto {    
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long id;
    private String codigo;
    private String nome;
    private String tipo;
    private BigDecimal valor;
    private Integer estoque;

    public Produto(DadosCadastroProduto dados) {
        this.codigo = dados.codigo();
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.valor = dados.valor();
        this.estoque = dados.estoque();
    }

}

package br.edu.ifrs.resinga.MeuMercado.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class Produto {    
    
    @Id /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/ //autoincrement
    private Long codigo;
    private String nome;
    private String tipo;
    private double valor;
    private int estoque;

    public Produto(DadosCadastroProduto dados) {
        this.codigo = dados.codigo();
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.valor = dados.valor();
        this.estoque = dados.estoque();
    }

}

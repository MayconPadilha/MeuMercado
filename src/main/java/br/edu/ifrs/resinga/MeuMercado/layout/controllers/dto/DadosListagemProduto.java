package br.edu.ifrs.resinga.MeuMercado.layout.controllers.dto;

import java.math.BigDecimal;

import br.edu.ifrs.resinga.MeuMercado.model.Produto;

public record DadosListagemProduto(
    Long id,
    String codigo,
    String nome,
    String tipo,
    BigDecimal valor,
    Integer estoque
) {
    public DadosListagemProduto(Produto produto){
        this(produto.getId(), produto.getCodigo(), produto.getNome(), produto.getTipo(), produto.getValor(), produto.getEstoque());
    }
    
}

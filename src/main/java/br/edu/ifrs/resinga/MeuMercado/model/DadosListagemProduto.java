package br.edu.ifrs.resinga.MeuMercado.model;

import java.math.BigDecimal;

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

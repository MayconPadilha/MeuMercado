package br.edu.ifrs.resinga.MeuMercado.layout.controllers.dto;

import java.math.BigDecimal;

public record DadosCadastroProduto(
    // @NotBlank
    String codigo, 

    // @NotBlank
    String nome, 
    
    // @NotBlank
    String tipo,
    
    // @NotBlank
    BigDecimal valor,
    
    // @NotNull
    int estoque

) {
    
}

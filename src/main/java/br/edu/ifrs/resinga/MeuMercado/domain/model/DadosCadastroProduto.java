package br.edu.ifrs.resinga.MeuMercado.domain.model;

public record DadosCadastroProduto(
    // @NotBlank
    Long codigo, 

    // @NotBlank
    String nome, 
    
    // @NotBlank
    String tipo,
    
    // @NotBlank
    double valor,
    
    // @NotNull
    int estoque

) {
    
}

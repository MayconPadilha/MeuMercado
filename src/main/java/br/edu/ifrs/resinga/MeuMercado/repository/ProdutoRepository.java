package br.edu.ifrs.resinga.MeuMercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifrs.resinga.MeuMercado.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    void deleteByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    Produto findByCodigo(String codigo);
    
}

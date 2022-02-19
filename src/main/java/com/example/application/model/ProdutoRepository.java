package com.example.application.model;

// @author Henrique Andrew da Silva

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProdutoRepository extends CrudRepository<Produto, Long>{
    
    @Override
    List<Produto> findAll();
    
    Produto findById(long id);
    
    @Query(value = "from Produto c where lower(c.descricao) like '%'||lower(?1)||'%'")
    List<Produto> findByDescricao(String descricao);

}

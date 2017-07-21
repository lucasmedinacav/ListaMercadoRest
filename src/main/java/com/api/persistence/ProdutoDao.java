package com.api.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.bindings.ProdutoListaResponse;
import com.api.representations.Produto;

@Repository
public interface ProdutoDao extends JpaRepository<Produto, Long> {
	
	@Query(nativeQuery = true)
	List<ProdutoListaResponse> buscaProdutosDaLista(@Param("idLista") Long idLista);
	
	@Query(nativeQuery = true)
	List<ProdutoListaResponse> buscaProdutosNaoCadastradosNaLista(@Param("idLista") Long idLista);
}
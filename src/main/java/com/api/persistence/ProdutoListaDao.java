package com.api.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.representations.ProdutoLista;

@Repository
public interface ProdutoListaDao extends JpaRepository<ProdutoLista, Long> {
	
	@Modifying
	@Query("DELETE FROM ProdutoLista p where p.produto.id = :produtoId and p.lista.id = :listaId")
	void excluirProdutoDaLista(@Param("produtoId") Long produtoId,@Param("listaId")  Long listaId);
}
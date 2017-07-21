package com.api.services;

import java.util.List;
import org.springframework.stereotype.Component;
import com.api.bindings.ProdutoListaRequest;
import com.api.bindings.ProdutoListaResponse;

@Component("produtoListaService")
public interface ProdutoListaService {
	
	void adicionaProdutoNaLista(ProdutoListaRequest request);
	
	List<ProdutoListaResponse> buscaProdutosDaLista(Long idLista);
	
	List<ProdutoListaResponse> buscaProdutosNaoCadastradosNaLista(Long idLista);
	
	void excluirProdutoDaLista(Long produtoId, Long listaId);
}

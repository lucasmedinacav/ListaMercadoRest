package com.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.bindings.ProdutoListaRequest;
import com.api.bindings.ProdutoListaResponse;
import com.api.persistence.ListaDao;
import com.api.persistence.ProdutoDao;
import com.api.persistence.ProdutoListaDao;
import com.api.representations.Lista;
import com.api.representations.Produto;
import com.api.representations.ProdutoLista;

@Service
public class ProdutoListaServiceImpl implements ProdutoListaService {
	
	@Autowired
	private ProdutoListaDao dao;
	
	@Autowired
	private ListaDao listaDao;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Override
	public void adicionaProdutoNaLista(ProdutoListaRequest request) {
		Lista lista = listaDao.getOne(request.getIdLista());
		Produto produto = produtoDao.getOne(request.getIdProduto());
		ProdutoLista prodLista = new ProdutoLista(produto, lista, request.isFoiPego(), request.getQuantidade());
		dao.save(prodLista);
	}
	
	@Override
	public List<ProdutoListaResponse> buscaProdutosDaLista(Long idLista) {
		return produtoDao.buscaProdutosDaLista(idLista);
	}
	
	@Override
	public List<ProdutoListaResponse> buscaProdutosNaoCadastradosNaLista(Long idLista) {
		return produtoDao.buscaProdutosNaoCadastradosNaLista(idLista);
	}

	@Override
	public void excluirProdutoDaLista(Long produtoId, Long listaId) {
		dao.excluirProdutoDaLista(produtoId, listaId);
	}
}
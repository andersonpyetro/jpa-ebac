package br.com.pyetro;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.pyetro.dao.IProdutoDao;
import br.com.pyetro.dao.ProdutoDao;
import br.com.pyetro.domain.Produto;

public class ProdutoTest {
	
private IProdutoDao produtoDao;
	
	public ProdutoTest() {
		produtoDao = new ProdutoDao();
	}
	
	@Test
	public void cadastrar() {
		Produto prod = new Produto();
		prod.setCodigo("A1");
		prod.setDescricao("PRODUTO TESTE");
		prod.setNome("NOME DO PRODUTO");
		prod = produtoDao.cadastrar(prod);
		
		assertNotNull(prod);
		assertNotNull(prod.getId());
	}

}

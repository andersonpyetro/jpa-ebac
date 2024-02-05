package br.com.pyetro;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.pyetro.dao.CursoDao;
import br.com.pyetro.dao.ICursoDao;
import br.com.pyetro.domain.Curso;

public class CursoTest {
	
	private ICursoDao cursoDao;
	
	public CursoTest() {
		cursoDao = new CursoDao();
	}
	
	@Test
	public void cadastrar() {
		Curso curso = new Curso();
		curso.setCodigo("A1");
		curso.setDescricao("CURSO TESTE");
		curso.setNome("NOME DO CURSO");
		curso = cursoDao.cadastrar(curso);
		
		assertNotNull(curso);
		assertNotNull(curso.getId());
	}

}

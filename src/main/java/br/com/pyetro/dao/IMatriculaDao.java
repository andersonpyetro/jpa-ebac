package br.com.pyetro.dao;

import java.util.List;

import br.com.pyetro.domain.Curso;
import br.com.pyetro.domain.Matricula;

public interface IMatriculaDao {
	
	public Matricula cadastrar(Matricula mat);	
	Matricula buscarPorCodigoCurso(String codigoCurso);
	Matricula buscarPorCurso(Curso curso);
	Matricula buscarPorCodigoCursoCriteria(String codigoCurso);
	Matricula buscaPorCursoCriteria(Curso curso);
	List<Matricula> buscarTodos();
	void excluir(Matricula matricula);
	
	

}

package br.com.pyetro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.pyetro.domain.Curso;
import br.com.pyetro.domain.Matricula;

public class MatriculaDao implements IMatriculaDao{

	@Override
	public Matricula cadastrar(Matricula mat) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(mat);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
		return mat;
	
	}

	@Override
	public Matricula buscarPorCodigoCurso(String codigoCurso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT m FROM Matricula m ");
		sb.append("INNER JOIN Curso c on c = m.curso ");
		sb.append("WHERE c.codigo = :codigoCurso");
		
		em.getTransaction().begin();
		TypedQuery<Matricula> query = em.createQuery(sb.toString(), Matricula.class);
		query.setParameter("codigoCurso", codigoCurso);
		Matricula matricula = query.getSingleResult();
		
		em.close();
		emf.close();
		
		return matricula;
	}

	@Override
	public Matricula buscarPorCurso(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT m FROM Matricula m ");
		sb.append("INNER JOIN Curso c on c = m.curso ");
		sb.append("WHERE c.codigo = :codigoCurso"); 
		
		em.getTransaction().begin();
		TypedQuery<Matricula> query = em.createQuery(sb.toString(), Matricula.class);
		query.setParameter("curso", curso);
		Matricula matricula = query.getSingleResult();
		
		em.close();
		emf.close();
		
		return matricula;
	}

	@Override
	public Matricula buscarPorCodigoCursoCriteria(String codigoCurso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);		
		Root<Matricula> root = query.from(Matricula.class);
		Join<Object, Object> join = root.join("curso", JoinType.INNER);
		query.select(root).where(builder.equal(join.get("codigo"), codigoCurso));
		
		TypedQuery<Matricula> tpQuery = em.createQuery(query);
		Matricula matricula = tpQuery.getSingleResult();
		
		em.close();
		emf.close();	
		
		return matricula;
	}
	
	@Override
	public Matricula buscaPorCursoCriteria(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);		
		Root<Matricula> root = query.from(Matricula.class);
		Join<Object, Object> join = root.join("curso", JoinType.INNER);
		query.select(root).where(builder.equal(join, curso));
		
		TypedQuery<Matricula> tpQuery = em.createQuery(query);
		Matricula matricula = tpQuery.getSingleResult();
		
		em.close();
		emf.close();	
		
		return matricula;
	}

	@Override
	public List<Matricula> buscarTodos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Matricula> query = builder.createQuery(Matricula.class);		
		Root<Matricula> root = query.from(Matricula.class);
		query.select(root);
		
		TypedQuery<Matricula> tpQuery = em.createQuery(query);
		List<Matricula> list = tpQuery.getResultList();
		
		em.close();
		emf.close();
		
		return list;
	}

	@Override
	public void excluir(Matricula matricula) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		matricula = em.merge(matricula);
		em.remove(matricula);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
		
		
	}

	

}

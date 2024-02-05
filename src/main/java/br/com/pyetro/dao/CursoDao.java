package br.com.pyetro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.pyetro.domain.Curso;

public class CursoDao implements ICursoDao{

	@Override
	public Curso cadastrar(Curso curso) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(curso);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
		return curso;
	}

	@Override
	public void excluir(Curso cur) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		cur = em.merge(cur);
		em.remove(cur);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
		
		
	}

	@Override
	public List<Curso> buscarTodos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Curso> query = builder.createQuery(Curso.class);
		Root<Curso> root = query.from(Curso.class);
		query.select(root);
		
		TypedQuery<Curso> tpQuery = em.createQuery(query);
		List<Curso> list = tpQuery.getResultList();
		
		em.close();
		emf.close();
		return list;
	}

}

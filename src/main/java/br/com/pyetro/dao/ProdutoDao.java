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
import br.com.pyetro.domain.Produto;

public class ProdutoDao implements IProdutoDao{

	@Override
	public Produto cadastrar(Produto produto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return produto;
	}

	@Override
	public void excluir(Produto prod) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(prod);
		em.remove(prod);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
		
	}

	@Override
	public List<Produto> buscarTodos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAebac");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		query.select(root);
		
		TypedQuery<Produto> tpQuery = em.createQuery(query);
		List<Produto> list = tpQuery.getResultList();
		
		em.close();
		emf.close();
		
		return list;
		
	}

}

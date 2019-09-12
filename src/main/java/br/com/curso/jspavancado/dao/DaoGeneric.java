package br.com.curso.jspavancado.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.curso.jspavancado.JPAUtil;

public class DaoGeneric<T> {

	public void salvar(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entity);
		entityTransaction.commit();
		entityManager.close();
	}

	public T merge(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		T result = entityManager.merge(entity);

		entityTransaction.commit();
		entityManager.close();

		return result;

	}

	public void remove(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entity);

		entityTransaction.commit();
		entityManager.close();
	}

	public void removeById(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = JPAUtil.getPrimaryKey(entity);
		entityManager.createQuery("delete from " + entity.getClass().getName() + " where id = " + id).executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public T find(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(entity);
		T result = (T) entityManager.find(entity.getClass(), id);

		entityTransaction.commit();
		entityManager.close();

		return result;
	}

	public List<T> findAll(Class<T> entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<T> result = entityManager.createQuery("from " + entity.getName(), entity).getResultList();

		entityTransaction.commit();
		entityManager.close();

		return result;

	}
}

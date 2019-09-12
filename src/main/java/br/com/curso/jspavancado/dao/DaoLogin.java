package br.com.curso.jspavancado.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.curso.jspavancado.JPAUtil;
import br.com.curso.jspavancado.model.Usuario;

public class DaoLogin {

	public Usuario logar(String login, String senha) {
		try {
			EntityManager entityManager = JPAUtil.getEntityManager();
			Usuario usuario = entityManager
					.createQuery(
							"select new Usuario(u.id, u.login, u.senha, u.imagemMimeType) from "
									+ Usuario.class.getSimpleName() + " u where u.login = :login and u.senha = :senha",
							Usuario.class)
					.setParameter("login", login).setParameter("senha", senha).getSingleResult();
			entityManager.close();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

}

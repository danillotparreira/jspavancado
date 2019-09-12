package br.com.curso.jspavancado.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.curso.jspavancado.JPAUtil;
import br.com.curso.jspavancado.model.Usuario;

public class DaoUsuario extends DaoGeneric<Usuario> {

	@Override
	public List<Usuario> findAll(Class<Usuario> entity) {
		try {
			EntityManager entityManager = JPAUtil.getEntityManager();

			List<Usuario> result = entityManager
					.createQuery("SELECT new Usuario(e.id, e.login, e.senha, e.imagemMimeType) FROM "
							+ entity.getSimpleName() + " e", Usuario.class)
					.getResultList();

			entityManager.close();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

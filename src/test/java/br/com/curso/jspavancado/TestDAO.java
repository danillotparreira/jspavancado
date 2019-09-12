package br.com.curso.jspavancado;

import org.junit.Test;

import br.com.curso.jspavancado.dao.DaoGeneric;
import br.com.curso.jspavancado.model.Usuario;

public class TestDAO {

	@Test
	public void test() {
		DaoGeneric<Usuario> daoUsuario = new DaoGeneric<>();
		daoUsuario.merge(new Usuario().login("admin").senha("admin"));
		daoUsuario.merge(new Usuario().login("maria").senha("maria"));
		daoUsuario.merge(new Usuario().login("joao").senha("joao"));
		daoUsuario.merge(new Usuario().login("teste").senha("teste"));
		daoUsuario.merge(new Usuario().login("abc").senha("abc"));
	}

}

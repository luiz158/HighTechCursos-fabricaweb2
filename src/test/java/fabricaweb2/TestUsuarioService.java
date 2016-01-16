package fabricaweb2;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.DAOExceptio;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/springbeans.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestUsuarioService {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	@Qualifier("usuarioDAOJPA")
	UsuarioDAO usuarioDAO;

	@Test(expected = ServiceException.class)
	@Transactional
	public void testNaoDeveSalvar() throws ServiceException {
		Usuario usuario = new Usuario();
		usuario.setLogin("testsalvar2");
		usuario.setSenha("123");
		usuario.setNome("Test Nome 2");

		usuarioDAO.salvar(usuario);

		usuarioService.salvar(usuario);
	}

	@Test
	@Transactional
	public void testDeveSalvar() throws ServiceException {
		Usuario usuario = new Usuario();
		usuario.setLogin("testsalvar123");
		usuario.setSenha("123");
		usuario.setNome("Test Nome");

		usuarioService.salvar(usuario);
	}

	public void excluir(Usuario usuario) throws ServiceException {
		try {
			usuarioDAO.excluir(usuario);
		} catch (DAOExceptio e) {
			throw new ServiceException(e);
		}
	}

	public Usuario buscarPorId(int id) {
		return usuarioDAO.buscarPorId(id);
	}

	public List<Usuario> buscarTodos() {
		return usuarioDAO.buscarTodos();
	}

	public Usuario buscarLogin(String login) {
		return usuarioDAO.buscarLogin(login);
	}
	
}

package fabricaweb2;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.dao.DAOExceptio;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/META-INF/springbeans.xml")
@TransactionConfiguration //(transactionManager = "transactionManager")
public class TestUsuarioDAO2 {

	@Autowired
	UsuarioDAO usuarioDAO;

	@Test
	public void testSalvar() {
		// Criando novo usuario
		Usuario usuario = new Usuario();
		usuario.setLogin("cadu");
		usuario.setNome("Carlos Eduardo");
		usuario.setSenha("101112");

		usuario = usuarioDAO.salvar(usuario);

		Assert.assertNotNull(usuario.getId());
	}

	@Test
	@Transactional
	public void testExcluir() throws DAOExceptio {
		// Cria um novo usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setLogin("teste");
		usuario.setSenha("teste");

		// Salva usuario de teste
		Usuario usuSalvo = usuarioDAO.salvar(usuario);

		// Exclui usuario
		usuarioDAO.excluir(usuSalvo);

		// Busca por Id
		Usuario usuExcluido = usuarioDAO.buscarPorId(usuSalvo.getId());
		
		// Define correto se o objeto não for encontrado
		Assert.assertEquals(usuExcluido, null);
	}

	@Test
	public void testBuscarPorId() {
		// Criando um novo usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Jose Carlos");
		usuario.setLogin("zeca");
		usuario.setSenha("1234567890");

		Usuario usuSalvo = usuarioDAO.salvar(usuario);
		Integer idUsuSalvo = usuSalvo.getId();

		Usuario usuBuscado = usuarioDAO.buscarPorId(idUsuSalvo);

		Assert.assertEquals(usuSalvo, usuBuscado);
	}

	@Test
	public void testBuscarTodos() {
		// Cria um novo usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Teste");
		usuario.setLogin("teste");
		usuario.setSenha("teste");

		// Salva usuario de teste
		usuarioDAO.salvar(usuario);

		// Busca no banco
		List<Usuario> todos = usuarioDAO.buscarTodos();

		// Verifica se a lista é maior que zero
		Assert.assertTrue(todos.size() > 0);
	}

}

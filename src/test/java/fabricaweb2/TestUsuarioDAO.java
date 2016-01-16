package fabricaweb2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.dao.DAOExceptio;
import br.com.fabricadeprogramador.dao.UsuarioDAOJPA;
import br.com.fabricadeprogramador.entidade.Usuario;

public class TestUsuarioDAO {

	ClassPathXmlApplicationContext ctx;
	EntityManager em;
	UsuarioDAOJPA usuarioDAO;

	@Before
	public void init() {
		// Contexto do Spring
		ctx = new ClassPathXmlApplicationContext("file:src/main/resources/META-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		usuarioDAO = new UsuarioDAOJPA(em);
	}

	@After
	public void finaliza() {
		ctx.close();
	}

//	@Test
	public void testSalvar() {
		// Criando novo usuario
		Usuario usuario = new Usuario();
		usuario.setLogin("cadu");
		usuario.setNome("Carlos Eduardo");
		usuario.setSenha("101112");

		usuario = usuarioDAO.salvar(usuario);

		Assert.assertNotNull(usuario.getId());
	}

//	@Test
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

//	@Test
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

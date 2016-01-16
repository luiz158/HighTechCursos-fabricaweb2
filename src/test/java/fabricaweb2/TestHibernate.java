package fabricaweb2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.dao.UsuarioDAOJPA;
import br.com.fabricadeprogramador.entidade.Usuario;

public class TestHibernate {

	public static void main(String[] args) {
		
//		// Fabrica de EntityManager
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fabricaweb2");
//		
//		// Gerenciador de Entidades
//		EntityManager em = emf.createEntityManager();
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/resources/META-INF/springbeans.xml");
		EntityManagerFactory emf = (EntityManagerFactory)ctx.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
		
		//Criando objeto a ser persistido
		Usuario usuario = new Usuario();
		usuario.setNome("Maria");
		usuario.setLogin("mar");
		usuario.setSenha("123");
		
		UsuarioDAOJPA usuarioDAO = new UsuarioDAOJPA(em);
		usuarioDAO.salvar(usuario);
		
//		Usuario usuModificar = usuarioDAO.buscarPorId(1);
//		usuModificar.setLogin("jaojao");
//		usuarioDAO.salvar(usuModificar);
		
		ctx.close();
	}

}

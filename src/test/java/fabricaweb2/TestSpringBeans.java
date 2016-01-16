package fabricaweb2;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fabricadeprogramador.entidade.Usuario;

public class TestSpringBeans {

	//@Test
	public void testContextoSpring() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/resources/META-INF/springbeans.xml");

//		Usuario usu = (Usuario) ctx.getBean("usuario");
//		System.out.println(usu);
		
		BasicDataSource bds = (BasicDataSource) ctx.getBean("dataSourceFactory");
		System.out.println(bds.getUsername() + " " + bds.getPassword() + " " + bds.getDriverClassName());
		
		ctx.close();
	}

}

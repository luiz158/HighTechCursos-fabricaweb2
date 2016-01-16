package br.com.fabricadeprogramador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.dao.DAOExceptio;
import br.com.fabricadeprogramador.dao.UsuarioDAO;
import br.com.fabricadeprogramador.entidade.Usuario;

@Service
public class UsuarioService {

	@Autowired
	@Qualifier(value="usuarioDAOJPA")
	UsuarioDAO usuarioDAO;
	
	public Usuario salvar(Usuario u) throws ServiceException {
//		Usuario usuarioExistente = usuarioDAO.buscarLogin(u.getLogin());
//		
//		if (usuarioExistente != null ) {
//			throw new ServiceException("Usuário já existe!");
//		}
		return usuarioDAO.salvar(u);
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
	
}

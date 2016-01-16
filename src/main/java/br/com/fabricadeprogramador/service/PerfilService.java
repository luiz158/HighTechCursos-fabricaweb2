package br.com.fabricadeprogramador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.dao.DAOExceptio;
import br.com.fabricadeprogramador.dao.PerfilDAO;
import br.com.fabricadeprogramador.entidade.Perfil;

@Service
public class PerfilService {

	@Autowired
	@Qualifier(value="perfilDAOJPA")
	PerfilDAO perfilDAO;
	
	public Perfil salvar(Perfil p) throws ServiceException {
		return perfilDAO.salvar(p);
	}
	
	public void excluir(Perfil perfil) throws ServiceException {
		try {
			perfilDAO.excluir(perfil);
		} catch (DAOExceptio e) {
			throw new ServiceException(e);
		}		
	}
	
	public Perfil buscarPorId(int id) {
		return perfilDAO.buscarPorId(id);
	}
	
	public List<Perfil> buscarTodos() {
		return perfilDAO.buscarTodos();
	}
	
}

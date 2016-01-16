package br.com.fabricadeprogramador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.entidade.Usuario;

@Repository
public class UsuarioDAOJPA implements UsuarioDAO {

	@PersistenceContext
	EntityManager em;

	public UsuarioDAOJPA() {

	}

	public UsuarioDAOJPA(EntityManager em) {
		this.em = em;
	}

	// Insert ou Update
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario u = em.merge(usuario);
		return u;
	}

	@Transactional
	public void excluir(Usuario usuario) throws DAOExceptio {
		try {
			Usuario usuManaged = em.find(Usuario.class, usuario.getId());
			em.remove(usuManaged);
		} catch (Exception e) {
			throw new DAOExceptio("Não foi possível excluir!", e);
		}
	}

	public Usuario buscarPorId(int id) {
		return em.find(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		Query q = em.createQuery("SELECT u FROM Usuario u");
		return q.getResultList();
	}

	@Override
	public Usuario buscarLogin(String login) {
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.login=:loginParam");
			q.setParameter("loginParam", login);
			q.setMaxResults(1);
			return (Usuario) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
//			throw new DAOExceptio("Registro não encontrado!", e);
		}
	}

}

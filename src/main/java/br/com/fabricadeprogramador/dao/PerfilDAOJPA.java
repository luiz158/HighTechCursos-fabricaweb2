package br.com.fabricadeprogramador.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fabricadeprogramador.entidade.Perfil;

@Repository
public class PerfilDAOJPA implements PerfilDAO {

	@PersistenceContext
	EntityManager em;

	public PerfilDAOJPA() {

	}

	public PerfilDAOJPA(EntityManager em) {
		this.em = em;
	}

	// Insert ou Update
	@Transactional
	public Perfil salvar(Perfil perfil) {
		Perfil p = em.merge(perfil);
		return p;
	}

	@Transactional
	public void excluir(Perfil perfil) throws DAOExceptio {
		try {
			Perfil perfilManaged = em.find(Perfil.class, perfil.getId());
			em.remove(perfilManaged);
		} catch (Exception e) {
			throw new DAOExceptio("Não foi possível excluir!", e);
		}
	}

	public Perfil buscarPorId(int id) {
		return em.find(Perfil.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Perfil> buscarTodos() {
		Query q = em.createQuery("SELECT p FROM Perfil p");
		return q.getResultList();
	}

}

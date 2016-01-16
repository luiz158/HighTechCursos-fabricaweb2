package br.com.fabricadeprogramador.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fabricadeprogramador.entidade.Perfil;
import br.com.fabricadeprogramador.service.PerfilService;
import br.com.fabricadeprogramador.service.ServiceException;

@Controller(value = "perfilController")
public class PerfilController {

	@Autowired
	private PerfilService perfilService;
	private Perfil perfil = new Perfil();
	private List<Perfil> perfilList;

	public PerfilController() {
	}

	@PostConstruct
	public void init() {
		setPerfilList(perfilService.buscarTodos());
	}

	public void salvar() {
		try {
			// Salva o perfil
			perfilService.salvar(perfil);
			// Atualiza a lista
			perfilList = perfilService.buscarTodos();
			// Limpa o formulario
			perfil = new Perfil();
			
			MensagemUtil.mensagemInfo("Salvo com sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro("Não foi possível salvar!");
			e.printStackTrace();
		}
	}

	public void editar(Perfil p) {
		this.perfil = p;
	}

	public void excluir(Perfil p) {
		try {
			// Exclui o perfil
			perfilService.excluir(p);
			// Atualiza a lista
			perfilList.remove(p);
			
			MensagemUtil.mensagemInfo("Excluído com sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro("Não foi possível excluir!");
			e.printStackTrace();
		}
	}

	// Getters and Setters
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}

}

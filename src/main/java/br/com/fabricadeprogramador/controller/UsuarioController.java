package br.com.fabricadeprogramador.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.fabricadeprogramador.entidade.Usuario;
import br.com.fabricadeprogramador.service.ServiceException;
import br.com.fabricadeprogramador.service.UsuarioService;

@Controller(value = "usuarioController")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarioList;

	public UsuarioController() {
	}

	@PostConstruct
	public void init() {
		setUsuarioList(usuarioService.buscarTodos());
	}

	public void cancelar() {
		this.usuario = new Usuario();
	}
	public void salvar() {
		try {
			// Salva o usuario
			usuarioService.salvar(usuario);
			// Atualiza a lista
			usuarioList = usuarioService.buscarTodos();
			// Limpa o formulario
			usuario = new Usuario();
			
			MensagemUtil.mensagemInfo("Salvo com sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro("Não foi possível salvar!");
			e.printStackTrace();
		}
	}

	public void editar(Usuario u) {
		this.usuario = u;
	}

	public void excluir(Usuario u) {
		try {
			// Exclui o usuario
			usuarioService.excluir(u);
			// Atualiza a lista
			usuarioList.remove(u);
			
			MensagemUtil.mensagemInfo("Excluído com sucesso!");
		} catch (ServiceException e) {
			MensagemUtil.mensagemErro("Não foi possível excluir!");
			e.printStackTrace();
		}
	}

	// Getters and Setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

}

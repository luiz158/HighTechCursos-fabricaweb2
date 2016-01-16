package br.com.fabricadeprogramador.service;

import br.com.fabricadeprogramador.dao.DAOExceptio;

@SuppressWarnings("serial")
public class ServiceException extends Exception {

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(DAOExceptio e) {
		super(e);
	}

}

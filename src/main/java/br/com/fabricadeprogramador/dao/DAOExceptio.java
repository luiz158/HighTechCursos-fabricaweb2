package br.com.fabricadeprogramador.dao;

@SuppressWarnings("serial")
public class DAOExceptio extends Exception {

	public DAOExceptio(String msg, Exception e) {
		super(msg, e);
	}

}

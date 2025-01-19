package com.teste.pratico.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class JSFUtil {

	private static final String MSG_SUCESSO = "Operação realizada com sucesso";
	private static final String MSG_ERRO = "Operação não pode ser realizada";
	private static final String MSG_INCLUSAO_SUCESSO = "Cadastro realizado com sucesso";
	private static final String MSG_ALTERACAO_SUCESSO = "Alteração realizada com sucesso";
	private static final String MSG_REMOCAO_SUCESSO = "Exclusão realizada com sucesso";

	public static FacesContext getFaces() {
		return FacesContext.getCurrentInstance();
	}

	public static void adicionarMsgGlobalOperacaoSucesso() {
		setInfo(MSG_SUCESSO);
	}

	public static void adicionarMsgGlobalOperacaoErro() {
		setErro(MSG_ERRO);
	}

	public static void adicionarMsgGlobalCadastroSucesso() {
		setInfo(MSG_INCLUSAO_SUCESSO);
	}

	public static void adicionarMsgGlobalExclusaoSucesso() {
		setInfo(MSG_REMOCAO_SUCESSO);
	}

	public static void adicionarMsgGlobalAlteracaoSucesso() {
		setInfo(MSG_ALTERACAO_SUCESSO);
	}

	public void adicionarMensagemGlobal(Severity tipo, String msg) {
		getFaces().addMessage(null, new FacesMessage(tipo, msg, msg));
	}

	public static void setInfo(String id, String msg) {
		setMensagem(id, FacesMessage.SEVERITY_INFO, msg);
	}

	public static void setInfo(String msg) {
		setInfo(null, msg);
	}

	public static void setErro(String msg) {
		setErro(null, msg);
	}

	public static void setErro(String id, String msg) {
		setMensagem(id, FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void setAviso(String msg) {
		setMensagem(null, FacesMessage.SEVERITY_WARN, msg);
	}

	public static void setAviso(String id, String msg) {
		setMensagem(id, FacesMessage.SEVERITY_WARN, msg);
	}

	public void setMensagem(Severity tipo, String msg) {
		setMensagem(null, tipo, msg);
	}

	public static void setMensagem(String id, Severity tipo, String msg) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(tipo, msg, msg));
	}

}

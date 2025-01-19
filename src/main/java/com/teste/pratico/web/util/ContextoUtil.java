package com.teste.pratico.web.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Enumeration;

@Named
@ApplicationScoped
public class ContextoUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    private static final String IDENT_REQUEST_PARAM = "@";

    public ContextoUtil() {
    }

    @Produces
    @RequestScoped
    public FacesContext getFacesContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            throw new ContextNotActiveException("FacesContext is not active");
        } else {
            return context;
        }
    }

    public ExternalContext getExternalContext() {
        return this.facesContext.getExternalContext();
    }

    public String getRealPath() {
        return this.getExternalContext().getRealPath("");
    }

    public HttpSession getSessao() {
        return (HttpSession)this.getExternalContext().getSession(true);
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse)this.getExternalContext().getResponse();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest)this.getExternalContext().getRequest();
    }

    public void setParamSession(String key, Object object) {
        this.getSessao().setAttribute(key, object);
    }

    public Object getParamSession(String key) {
        return this.getSessao().getAttribute(key);
    }

    public void removeAttribute(String key) {
        this.getSessao().removeAttribute(key);
    }

    public Object getParamRequest(String nome) {
        return nome.startsWith("@") ? this.getParamSession(nome) : this.getParamSession("@" + nome);
    }

    public Object popParamRequest(String nome, Object defaultValue) {
        Object r = this.getParamRequest(nome);
        this.removeParamRequest(nome);
        return r == null ? defaultValue : r;
    }

    public Object popParamSession(String nome, Object defaultValue) {
        Object r = this.getParamSession(nome);
        this.removeParamRequest(nome);
        return r == null ? defaultValue : r;
    }

    public void removeParamRequest(String nome) {
        if (nome.startsWith("@")) {
            this.removeAttribute(nome);
        } else {
            this.removeAttribute("@" + nome);
        }

    }

    public void setParamRequest(String nome, Object valor) {
        if (nome.startsWith("@")) {
            this.setParamSession(nome, valor);
        } else {
            this.setParamSession("@" + nome, valor);
        }

    }

    public void clearRequestParams() {
        Enumeration attrs = this.getSessao().getAttributeNames();

        while(attrs.hasMoreElements()) {
            String key = (String)attrs.nextElement();
            if (key.startsWith("@")) {
                this.removeParamRequest(key);
            }
        }

    }
}

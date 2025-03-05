package br.com.ApiLivros.infra;

import java.util.Map;

public class ErroDeValidacao {
    private int status;
    private String message;
    private Map<String, String> errors;

    public ErroDeValidacao(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

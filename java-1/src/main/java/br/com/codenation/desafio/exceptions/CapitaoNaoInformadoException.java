package br.com.codenation.desafio.exceptions;

public class CapitaoNaoInformadoException extends RuntimeException {
    public CapitaoNaoInformadoException(String message) {
        super(message);
    }

    public CapitaoNaoInformadoException(String message, Throwable cause) {
        super(message, cause);
    }
}

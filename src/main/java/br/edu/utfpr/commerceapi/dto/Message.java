package br.edu.utfpr.commerceapi.dto;

import lombok.Getter;

/**
 * Classe que representa uma mensagem de resposta para os clientes HTTP.
 */
@Getter
public class Message {
    private String message;

    private Message(String message) {
        this.message = message;
    }

    /**
     * Cria um objeto Res contendo uma mensagem.
     * 
     * @param message
     * @return Res
     */
    public static Message build(String message) {
        return new Message(message);
    }

    /**
     * Cria um objeto Res contendo uma mensagem.
     * 
     * @param message
     * @return Res
     */
    public static Message b(String message) {
        return build(message);
    }
}

package com.natancode.restclient.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://swapi.info/api/")
public interface StarWarsRestClientService {

    public static final String ERROR_MESSAGE = "Fallback ";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("starships")
    @Timeout(value = 3000L)
    @Fallback(
            fallbackMethod = "getStarShipsFallback"
    )
    @CircuitBreaker(
            requestVolumeThreshold = 2,//quantas requisições para analisar se tem problema ou não (Nesse caso a cada 2 requests)
            failureRatio = .5, //porcentagem de falhas para assumir e dizer que vai derrubar o dijuntor, quebrar o circuito. De cada 2 se uma falhar vai abrir o circuito
            delay = 5000L, //após abrir o circuito, verifica se voltou a funcionar (quanto tempo vai aguardar até que verifique que está funcionando outra vez)
            successThreshold = 2//quantas transações tem que acontecer com sucesso para assumir que está funcionando e que pode fechar o circuito (se tiver 2 transações com sucesso quer dizer que está funcionando novamente)
    )
    public String getStarships();

    default String getStarShipsFallback() {
        return ERROR_MESSAGE;
    }

}

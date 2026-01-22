package com.natancode.healthcheck;

import com.natancode.restclient.services.StarWarsRestClientService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    @RestClient
    StarWarsRestClientService starWarsRestClientService;

    @Override
    public HealthCheckResponse call() {

        if (starWarsRestClientService.getStarships().contains(StarWarsRestClientService.ERROR_MESSAGE)) {
            return HealthCheckResponse.down("NÃO ESTOU PRONTO");
        } else {
            return HealthCheckResponse.up("ESTOU PRONTO");
        }
    }
}

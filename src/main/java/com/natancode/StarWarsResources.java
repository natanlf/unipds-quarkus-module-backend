package com.natancode;

import com.natancode.restclient.services.StarWarsRestClientService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/starwars")
@Consumes(MediaType.TEXT_PLAIN)
public class StarWarsResources {

    @RestClient
    private StarWarsRestClientService starWarsRestClientService;

    @GET
    public String getStarShips() {
        return starWarsRestClientService.getStarships();
    }

}

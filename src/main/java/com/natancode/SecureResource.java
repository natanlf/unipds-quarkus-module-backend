package com.natancode;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

import java.util.Arrays;
import java.util.HashSet;


@Path("secure")
@RequestScoped //o escopo que acontece de tudo pertence sempre a request
public class SecureResource {

    @Claim(standard = Claims.preferred_username)
    private String userName;

    @GET
    @Path("claim")
    @RolesAllowed("Subscriber")
    public String getClaim() {
        //generateToken();
        return userName;
    }

    private void generateToken() {
        String token = Jwt.issuer("https://example.com/issuer")
                .upn("jdoe@quarkus.io")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .sign();
        System.out.println("Token");
        System.out.println(token);
    }

}

package com.natancode;

import com.natancode.domain.Person;
import io.micrometer.core.annotation.Counted;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Path("/count")
    @GET
    @Counted(value = "counted.getPerson")
    public List<Person> getPerson() {
        return Person.listAll();
    }

    @GET
    public List<Person> findAll() {
        return Person.findAll().list();
    }

    @Path("findByBirthDate")
    @GET
    public List<Person> findByBirthDate(@QueryParam("birthDate") int birthDate) {
        return Person.findByBirthDate(birthDate);
    }

}

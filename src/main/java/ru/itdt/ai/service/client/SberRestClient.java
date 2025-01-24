package ru.itdt.ai.service.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;

import static jakarta.ws.rs.core.HttpHeaders.*;
import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RegisterRestClient(configKey = "sber-api")
public interface SberRestClient {
    @POST
    @Path("/oauth")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    @ClientHeaderParam(name = CONTENT_TYPE, value = APPLICATION_FORM_URLENCODED)
    @ClientHeaderParam(name = ACCEPT, value = APPLICATION_JSON)
    @ClientHeaderParam(name = "RqUID", value = "${sber.RqUID}")
    @ClientHeaderParam(name = AUTHORIZATION, value = "${sber.authorization-key}")
    Response getToken(@RestForm("scope") String bean);
}

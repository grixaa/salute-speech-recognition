package ru.itdt.ai.controller;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.itdt.ai.service.AuthenticationService;

@Path("/recognition")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class SpeechRecognitionController {
    AuthenticationService authenticationService;

    @POST
    public Response testGetToken() {
        String token = authenticationService.getToken();
        return Response.ok().build();
    }
}
package br.com.mineradora.controller;

import br.com.mineradora.repository.OpportunityRepository;
import br.com.mineradora.service.OpportunityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.Date;

@Path("/api/opportunity")
public class OpportunityController {

    @Inject
    OpportunityService opportunityService;

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        try {
            return Response.ok(opportunityService.generateCSVOpportunityReport(),
                            MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition",
                            "attachment; filename = " + new Date() + "--opportunidades-venda.csv").build();
        } catch (ServerErrorException serverErrorException) {
            return Response.serverError().build();
        }
    }
}

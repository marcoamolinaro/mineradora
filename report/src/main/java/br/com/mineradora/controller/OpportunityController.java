package br.com.mineradora.controller;

import br.com.mineradora.dto.OpportunityDTO;
import br.com.mineradora.service.OpportunityService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/api/opportunity")
@Authenticated
public class OpportunityController {

    @Inject
    JsonWebToken jsonWebToken;
    @Inject
    OpportunityService opportunityService;

    @GET
    @Path("/report")
    @RolesAllowed({"user","manager"})
    public List<OpportunityDTO> generateReport() {
        return opportunityService.generateOpportunityData();
    }
}

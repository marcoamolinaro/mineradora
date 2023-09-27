package br.com.mineradora.service;

import br.com.mineradora.client.ReportRestClient;
import br.com.mineradora.dto.OpportunityDTO;
import br.com.mineradora.utils.CSVHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
@Traced
public class ReportServiceImpl implements ReportService {

    @Inject
    @RestClient
    ReportRestClient reportRestClient;

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {

        List<OpportunityDTO> opportunityData = reportRestClient.requestOpportunitiesData();
        return CSVHelper.OpportunitiesToCSV(opportunityData);

    }

    @Override
    public List<OpportunityDTO> getOpportunitiesData() {

        return reportRestClient.requestOpportunitiesData();

    }

}

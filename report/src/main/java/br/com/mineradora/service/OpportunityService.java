package br.com.mineradora.service;

import br.com.mineradora.dto.OpportunityDTO;
import br.com.mineradora.dto.ProposalDTO;
import br.com.mineradora.dto.QuotationDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface OpportunityService {
    void buildOpportunity(ProposalDTO proposal);

    void saveQuotation(QuotationDTO quotation);

    List<OpportunityDTO> generateOpportunityData();

    ByteArrayInputStream generateCSVOpportunityReport();
}

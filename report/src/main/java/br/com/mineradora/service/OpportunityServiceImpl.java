package br.com.mineradora.service;

import br.com.mineradora.dto.OpportunityDTO;
import br.com.mineradora.dto.ProposalDTO;
import br.com.mineradora.dto.QuotationDTO;
import br.com.mineradora.entity.OpportunityEntity;
import br.com.mineradora.entity.QuotationEntity;
import br.com.mineradora.repository.OpportunityRepository;
import br.com.mineradora.repository.QuotationRepository;
import br.com.mineradora.util.CSVHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    @Override
    public void buildOpportunity(ProposalDTO proposal) {
        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();

        Collections.reverse(quotationEntities);

        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setDate(new Date());
        opportunity.setCustomer(proposal.getCustomer());
        opportunity.setPriceTonne(proposal.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);
    }

    @Override
    public void saveQuotation(QuotationDTO quotation) {

    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return null;
    }

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {

        List<OpportunityDTO> opportunityList = new ArrayList<>();

        opportunityRepository.findAll().list().forEach(item -> {
            opportunityList.add(OpportunityDTO
                    .builder()
                    .proposalId(item.getProposalId())
                    .customer(item.getCustomer())
                    .priceTonne(item.getPriceTonne())
                    .lastDollarQuotation(item.getLastDollarQuotation())
                    .build());
        });

        return CSVHelper.OpportunitiesToCSV(opportunityList);
    }
}
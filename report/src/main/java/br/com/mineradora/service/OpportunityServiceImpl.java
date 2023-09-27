package br.com.mineradora.service;

import br.com.mineradora.dto.OpportunityDTO;
import br.com.mineradora.dto.ProposalDTO;
import br.com.mineradora.dto.QuotationDTO;
import br.com.mineradora.entity.OpportunityEntity;
import br.com.mineradora.entity.QuotationEntity;
import br.com.mineradora.repository.OpportunityRepository;
import br.com.mineradora.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
        opportunity.setProposalId(proposal.getProposalId());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);
    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {
        QuotationEntity createQuotation = new QuotationEntity();

        createQuotation.setDate(new Date());
        createQuotation.setCurrencyPrice(quotation.getCurrencyPrice());

        quotationRepository.persist(createQuotation);
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {]
        List<OpportunityDTO> opportunities = new ArrayList<>();

        opportunityRepository
                .findAll()
                .stream()
                .forEach(item-> {oportunities.add(OpportunityDTO
                        .builder()
                        .proposalId(item.getProposalId())
                        .priceTonne(item.getPriceTonne())
                        .customer(item.getCustomer())
                        .lastDollarQuotation(item.getLastDollarQuotation())
                        .build());
                });

        return opportunities;
    }
}
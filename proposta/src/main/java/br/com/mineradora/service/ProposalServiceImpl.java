package br.com.mineradora.service;

import br.com.mineradora.dto.ProposalDTO;
import br.com.mineradora.dto.ProposalDetailsDTO;
import br.com.mineradora.entity.ProposalEntity;
import br.com.mineradora.message.KafkaEvent;
import br.com.mineradora.repository.ProposalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Date;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService{

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaEvent;

    @Override
    public ProposalDetailsDTO findFullProposal(long id) {
        ProposalEntity proposal = proposalRepository.findById(id);
        return ProposalDetailsDTO
                .builder()
                .proposalId(proposal.getId())
                .country(proposal.getCountry())
                .customer(proposal.getCustomer())
                .proposalValidityDays(proposal.getProposalValidityDays())
                .priceTonne(proposal.getPriceTonne())
                .tonnes(proposal.getTonnes())
                .build();
    }

    @Override
    @Transactional
    public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {

        ProposalDTO proposal = buildAndSaveNewProposal(proposalDetailsDTO);
        kafkaEvent.sendNewKafkaEvent(proposal);
    }

    @Override
    @Transactional
    public void removeProposal(long id) {
        proposalRepository.deleteById(id);
    }

    private ProposalDTO buildAndSaveNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        try {
            ProposalEntity proposal = new ProposalEntity();
            proposal.setCreated(new Date());
            proposal.setProposalValidityDays(proposalDetailsDTO.getProposalValidityDays());
            proposal.setCountry(proposalDetailsDTO.getCountry());
            proposal.setCustomer(proposalDetailsDTO.getCustomer());
            proposal.setPriceTonne(proposalDetailsDTO.getPriceTonne());
            proposal.setTonnes(proposalDetailsDTO.getTonnes());

            proposalRepository.persist(proposal);

            return ProposalDTO
                    .builder()
                    .proposalId(proposalRepository.findByCustomer(proposal.getCustomer()).get().getId())
                    .priceTonne(proposalDetailsDTO.getPriceTonne())
                    .customer(proposalDetailsDTO.getCustomer())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

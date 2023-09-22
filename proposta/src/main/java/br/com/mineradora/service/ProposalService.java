package br.com.mineradora.service;

import br.com.mineradora.dto.ProposalDetailsDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProposalService {

    ProposalDetailsDTO findFullProposal(long id);

    void createNewProposal(ProposalDetailsDTO proposalDetailsDTO);

    void removeProposal(long id);
}

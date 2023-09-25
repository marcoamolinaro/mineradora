package br.com.mineradora.repository;

import br.com.mineradora.entity.OpportunityEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OpportunityRepository implements PanacheRepository<OpportunityEntity> {

}

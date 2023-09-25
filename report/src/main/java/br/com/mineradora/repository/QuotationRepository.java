package br.com.mineradora.repository;

import br.com.mineradora.entity.QuotationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotationRepository implements PanacheRepository<QuotationEntity> {
}

package br.com.mineradora.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "proposal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String customer;

    @Column(name = "price-tonne")
    private BigDecimal priceTonne;

    private Integer tonnes;

    private String country;

    @Column(name = "proposal-validity-days")
    private Integer proposalValidityDays;

    private Date created;
}

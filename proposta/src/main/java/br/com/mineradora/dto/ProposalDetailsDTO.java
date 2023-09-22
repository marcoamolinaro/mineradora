package br.com.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
public class ProposalDetailsDTO {
    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private Integer tonnes;
    private String country;
    private Integer proposalValidityDays;
}

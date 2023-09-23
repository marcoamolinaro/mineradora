package br.com.mineradora.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream
    OpportunitiesToCSV(List<OpportunityDTO> opportunities) {
        final CSVFormat format =
                CSVFormat.DEFAULT.withHeader("ID Proposta", "Cliente", "Preço por Tonelada",
                        "Melhor Cotação da Moeda");

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out) format); {
                for (OpportinyDTO opps : opportunities) {
                    List<String> data = Arrays.asList(String.
                            valueOf(opps.getPropoalId()), opps.getCustomer(),
                            String.valueOf(opps.getPriceTonne()), String.valueOf(opps.getLastDolarQuotation()));
                    csvPrinter.printRecord(data);
                }
            }

            csvPrinter.flush();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fail to import data to CSV file:" + e.getMessage());
        }
    }
}

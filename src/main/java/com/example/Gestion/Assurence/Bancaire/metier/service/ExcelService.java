package com.example.Gestion.Assurence.Bancaire.metier.service;

import com.example.Gestion.Assurence.Bancaire.dao.entities.Contrat;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private final ContratService contratService;

    public ExcelService(ContratService contratService) {
        this.contratService = contratService;
    }

    public void importExcelFile(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<Contrat> contrats = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Skip header row
                continue;
            }
            Contrat contrat = new Contrat();
            contrat.setCodeClient(row.getCell(0).getStringCellValue());
            contrat.setPartenaireCommercial(row.getCell(1).getStringCellValue());
            contrat.setCei(row.getCell(2).getStringCellValue());
            contrat.setAgence(row.getCell(3).getStringCellValue());
            // Continuez à mapper les autres champs...

            contrats.add(contrat);
        }

        contratService.saveAll(contrats);
        workbook.close();
    }
}




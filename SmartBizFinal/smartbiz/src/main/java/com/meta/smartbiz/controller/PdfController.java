package com.meta.smartbiz.controller;

import com.meta.smartbiz.dto.PartyDto;
import com.meta.smartbiz.service.PartyService;
import com.meta.smartbiz.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private PartyService partyService;

    @GetMapping("/parties")
    public ResponseEntity<byte[]> downloadPartiesPdf() {
        try {
            List<PartyDto> parties = partyService.getAllParties();
            byte[] pdfBytes = pdfService.generatePartiesPdf(parties);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", 
                    "parties_report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/party-ledger/{id}")
    public ResponseEntity<byte[]> downloadPartyLedgerPdf(@PathVariable Long id) {
        try {
            Optional<PartyDto> partyOpt = partyService.getPartyById(id);
            if (partyOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            PartyDto party = partyOpt.get();
            // For now, pass empty transactions list - in a real app, you'd fetch actual transactions
            byte[] pdfBytes = pdfService.generatePartyLedgerPdf(party, List.of());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", 
                    "ledger_" + party.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


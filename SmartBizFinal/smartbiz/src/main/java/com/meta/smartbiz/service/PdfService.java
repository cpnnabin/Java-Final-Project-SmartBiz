package com.meta.smartbiz.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.meta.smartbiz.dto.PartyDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {

    public byte[] generatePartiesPdf(List<PartyDto> parties) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Title
            Paragraph title = new Paragraph("Parties Report")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Generated date
            Paragraph date = new Paragraph("Generated on: " + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(date);

            // Add some space
            document.add(new Paragraph("\n"));

            // Create table
            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));

            // Table headers
            table.addHeaderCell(new Cell().add(new Paragraph("Party Name").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Type").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Phone").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Email").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Current Balance").setBold()));

            // Table data
            for (PartyDto party : parties) {
                table.addCell(new Cell().add(new Paragraph(party.getName() != null ? party.getName() : "")));
                table.addCell(new Cell().add(new Paragraph(party.getPartyType() != null ? party.getPartyType().toString() : "")));
                table.addCell(new Cell().add(new Paragraph(party.getPhone() != null ? party.getPhone() : "")));
                table.addCell(new Cell().add(new Paragraph(party.getEmail() != null ? party.getEmail() : "")));
                table.addCell(new Cell().add(new Paragraph("₹" + String.format("%.2f", 
                        party.getCurrentBalance() != null ? party.getCurrentBalance() : 0.0))));
            }

            document.add(table);

            // Summary
            document.add(new Paragraph("\n"));
            Paragraph summary = new Paragraph("Total Parties: " + parties.size())
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(summary);

            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating PDF: " + e.getMessage());
        }

        return baos.toByteArray();
    }

    public byte[] generatePartyLedgerPdf(PartyDto party, List<Object> transactions) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Title
            Paragraph title = new Paragraph("Party Ledger")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Party details
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Party Name: " + party.getName()).setBold());
            document.add(new Paragraph("Party Type: " + party.getPartyType()));
            if (party.getPhone() != null) {
                document.add(new Paragraph("Phone: " + party.getPhone()));
            }
            if (party.getEmail() != null) {
                document.add(new Paragraph("Email: " + party.getEmail()));
            }
            document.add(new Paragraph("Current Balance: ₹" + String.format("%.2f", 
                    party.getCurrentBalance() != null ? party.getCurrentBalance() : 0.0)));

            // Generated date
            Paragraph date = new Paragraph("Generated on: " + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(date);

            // Add some space
            document.add(new Paragraph("\n"));

            // Create transactions table
            Table table = new Table(UnitValue.createPercentArray(new float[]{2, 3, 2, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));

            // Table headers
            table.addHeaderCell(new Cell().add(new Paragraph("Date").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Description").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Debit").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Credit").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Balance").setBold()));

            // For now, add a placeholder row since we don't have transaction data yet
            table.addCell(new Cell().add(new Paragraph("Opening")));
            table.addCell(new Cell().add(new Paragraph("Opening Balance")));
            table.addCell(new Cell().add(new Paragraph("")));
            table.addCell(new Cell().add(new Paragraph("")));
            table.addCell(new Cell().add(new Paragraph("₹" + String.format("%.2f", 
                    party.getCurrentBalance() != null ? party.getCurrentBalance() : 0.0))));

            document.add(table);

            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating PDF: " + e.getMessage());
        }

        return baos.toByteArray();
    }
}


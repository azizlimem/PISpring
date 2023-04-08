package com.example.marketplace.services;

import com.example.marketplace.entities.Commande;
import com.example.marketplace.entities.FactureCommande;
import com.example.marketplace.entities.Panier;
import com.example.marketplace.repository.ICommandeRepo;
import com.example.marketplace.repository.IFactureCommandeRepo;
import com.example.marketplace.repository.IPanierRepo;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@AllArgsConstructor
public class PDFGeneratorServices {
    private final IFactureCommandeRepo factureCommandeRepo;
    private final IPanierRepo panierRepo;
    private final ICommandeRepo commandeRepo;

    public void export(HttpServletResponse response,Integer id, Integer idp) throws IOException {
                                       // FactureCommande factureCommande=factureCommandeRepo.findById(id).orElse(null);
        Commande commande=commandeRepo.findById(id).orElse(null);
        Panier panier=panierRepo.findById(idp).orElse(null);
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.RED);



// Ajouter un en-tête de page
        HeaderFooter head = new HeaderFooter(new Phrase(""), false);
        head.setBorder(Rectangle.NO_BORDER);
        document.setHeader(head);

        HeaderFooter footer = new HeaderFooter(new Phrase(""), true);
        footer.setBorder(Rectangle.NO_BORDER);
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.setFooter(footer);


       // String dateStr = "Date: " + commande.getDateCommande();
       // int pageNumber = writer.getPageNumber();
      //  PdfContentByte canvas = writer.getDirectContent();
      //  ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(dateStr), document.right(), document.top(), 0);
        //test

                                            ////paragh

                                              //haka njareb fles couleurs

        Paragraph header = new Paragraph("Facture", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Color.BLUE));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLUE);


        Paragraph date = new Paragraph("Date: " + commande.getDateCommande(), infoFont);
        date.setSpacingAfter(20f);
        document.add(date);

        Paragraph nom=new Paragraph("Nom et Prenom:"  + panier.getUser().getFirstName()+" "+panier.getUser().getLastName(), infoFont);
        nom.setSpacingAfter(20f);
        document.add(nom);

        Paragraph prix=new Paragraph("Prix Facture:"  + commande.getFactureCommandes().getPrixFacture()  + ":dt", infoFont );
        prix.setSpacingAfter(20f);
        document.add(prix);

        Paragraph remise=new Paragraph("Remise $ :" +panier.getRemise(), infoFont);
        document.add(remise);


        Paragraph paragraph=new Paragraph("merci pour votre confiance!", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.BLUE));
        paragraph.setSpacingBefore(20f);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.close();

    }
}

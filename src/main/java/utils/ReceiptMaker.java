package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptMaker {

	public void createPdf(){
		try {
			OutputStream file  = new FileOutputStream(new File("recuue.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document,file);

			Image image = Image.getInstance("src/main/resources/assests/image/logo_la_salle.png");
			image.scaleAbsolute(200,23);
			image.setAlignment(Element.ALIGN_LEFT);

			Paragraph recu = new Paragraph("Recue de paiement de tenue scolaire");
			recu.setAlignment(Element.ALIGN_CENTER);

			Phrase nomS = new Phrase(" Ouedraogo Karim");
			nomS.setFont(FontFactory.getFont(String.valueOf(Font.UNDERLINE)));

			Phrase montantS = new Phrase("25000");
			montantS.setFont(FontFactory.getFont(String.valueOf(Font.UNDERLINE)));

			Phrase typeT = new Phrase("tenue de sport");
			typeT.setFont(FontFactory.getFont(String.valueOf(Font.UNDERLINE)));

			Paragraph nom = new Paragraph("Recu de : Ouedraogo Karim ");
			Paragraph montant = new Paragraph("Somme de:  25000");
			Paragraph  type = new Paragraph("Type de Tenue:   tenue de sport");


			document.open();
			document.add(image);
			document.add(recu);
			document.add(nom);
			document.add(montant);
			document.add(type);
			document.close();


		}catch (IOException| DocumentException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}
}

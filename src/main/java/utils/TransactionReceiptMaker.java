package utils;


import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class TransactionReceiptMaker {

	public void createPdf(String matricule, String nom,String prenom,String taille,int nombre,String model ,String type, float montant){
		try {
			Document document = new Document();
			String FILE = "Recue.pdf";
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addContent(matricule,nom,prenom,type,montant,taille, nombre,model,document);
			document.close();
		}catch (Exception exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}
	}

	private void addContent(String matricule, String nom,String prenom, String type, float montant,String taille,int nombre,String model,Document document) throws DocumentException {

		try {

			Image image = Image.getInstance("src/main/resources/assests/image/logo_la_salle.png");
			image.scaleAbsolute(540f, 72f);

			PdfPTable irdTable = new PdfPTable(3);
			irdTable.addCell(getIRDCell("N° Reçu"));
			irdTable.addCell(getIRDCell(" Date"));
			irdTable.addCell(getIRDCell("Reçu Par"));
			irdTable.addCell(getIRDCell("0001"));
			irdTable.addCell(getIRDCell("12-Nov-2020"));
			irdTable.addCell(getIRDCell("Soraya Ouedraogo"));

			PdfPTable irhTable = new PdfPTable(3);
			irhTable.setWidthPercentage(100);

			Paragraph yo = new Paragraph("");
			yo.setIndentationLeft(1);

			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("Invoice", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
			PdfPCell invoiceTable = new PdfPCell(irdTable);
			invoiceTable.setBorder(0);
			irhTable.addCell(invoiceTable);

			FontSelector fs = new FontSelector();
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
			fs.addFont(font);
			Phrase bill = fs.process("Facturé A:"); // customer information
			Paragraph name = new Paragraph(nom + prenom);
			name.setIndentationLeft(20);
			Paragraph matriculeP = new Paragraph(matricule);
			matriculeP.setIndentationLeft(20);

			PdfPTable billTable = new PdfPTable(6);
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[]{1, 2, 5, 2, 1, 2});
			billTable.setSpacingBefore(30.0f);
			billTable.addCell(getBillHeaderCell("Index"));
			billTable.addCell(getBillHeaderCell("Type de Tenue"));
			billTable.addCell(getBillHeaderCell("Model"));
			billTable.addCell(getBillHeaderCell("Taille"));
			billTable.addCell(getBillHeaderCell("Qty"));
			billTable.addCell(getBillHeaderCell("Prix"));

			billTable.addCell(getBillRowCell("1"));
			billTable.addCell(getBillRowCell(type));
			billTable.addCell(getBillRowCell(model));
			billTable.addCell(getBillRowCell(taille));
			billTable.addCell(getBillRowCell(String.valueOf(nombre)));
			billTable.addCell("5000");

			for (int i = 0; i <= 13; i++) {
				billTable.addCell(getBillRowCell(" "));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));

			}

			PdfPTable validity = new PdfPTable(1);
			validity.setWidthPercentage(100);
			validity.addCell(getValidityCell(" "));
			validity.addCell(getValidityCell("Warranty"));
			validity.addCell(getValidityCell(" * Products purchased comes with 1 year national warranty \n   (if applicable)"));
			validity.addCell(getValidityCell(" * Warranty should be claimed only from the respective manufactures"));
			PdfPCell summaryL = new PdfPCell(validity);
			summaryL.setColspan(3);
			summaryL.setPadding(1.0f);
			billTable.addCell(summaryL);


			PdfPTable accounts = new PdfPTable(2);
			accounts.setWidthPercentage(100);

			accounts.addCell(getAccountsCell("Total"));
			accounts.addCell(getAccountsCellR(String.valueOf(montant)));
			PdfPCell summaryR = new PdfPCell(accounts);
			summaryR.setColspan(3);
			billTable.addCell(summaryR);


		document.add(image);
		document.add(irhTable);
		document.add(bill);
		document.add(name);
		document.add(matriculeP);
		document.add(billTable);
		document.add(yo);
		}catch (IOException exception){
			Logger.getGlobal().log(Level.SEVERE,exception.getMessage());
		}

		System.out.println("Pdf created successfully");

	}


	public static PdfPCell getIRHCell(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
		/*	font.setColor(BaseColor.GRAY);*/
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		return cell;
	}

	public static PdfPCell getBillHeaderCell(String text) {
		FontSelector fs = new FontSelector();
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		return cell;
	}

	public static PdfPCell getBillRowCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getBillFooterCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getValidityCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setBorder(0);
		return cell;
	}

	public static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding (5.0f);
		return cell;
	}
	public static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
		cell.setPadding (5.0f);
		cell.setPaddingRight(20.0f);
		return cell;
	}

	public static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}

}

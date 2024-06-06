package com.BankingApplication.BankApplication.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.StatementDao;
import com.BankingApplication.BankApplication.entity.Statement;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Service
public class SaveAccountStatementsToPDF {

	
	
	@Autowired
	private StatementDao statementDao;
	
	public void saveStatementsToPDF(int accountNumber) throws FileNotFoundException {
		String path="C:\\Users\\Trainer\\Desktop\\springbootPdf\\AccountStatements"+accountNumber+".pdf";
		PdfWriter pdfWriter=new PdfWriter(path);
		PdfDocument pdfDocument=new PdfDocument(pdfWriter);
		Document document=new Document(pdfDocument);
		
		float[] columnWidth= {200f,200f,200f,200f,200f,200f};
		Table table=new Table(columnWidth);
		table.addHeaderCell(new Cell().add(new Paragraph("statementId")));
		table.addHeaderCell(new Cell().add(new Paragraph("amount")));
		table.addHeaderCell(new Cell().add(new Paragraph("transactionType")));
		table.addHeaderCell(new Cell().add(new Paragraph("date")));
		table.addHeaderCell(new Cell().add(new Paragraph("accountNumber")));
		table.addHeaderCell(new Cell().add(new Paragraph("accountHolderName")));
		
		List<Statement> statements=statementDao.getAllStatements(accountNumber);
		
		for (Statement statement : statements) {
			table.addCell(new Cell().add(new Paragraph(Integer.toString(statement.getStatementId()))));
			table.addCell(new Cell().add(new Paragraph(Double.toString(statement.getAmount()))));
			table.addCell(new Cell().add(new Paragraph(statement.getTransactionType().toString())));
			table.addCell(new Cell().add(new Paragraph(statement.getDate().toString())));
			table.addCell(new Cell().add(new Paragraph(Long.toString(statement.getToAccount().getAccountNumber()))));
			table.addCell(new Cell().add(new Paragraph(statement.getToAccount().getAccountHolderName())));
		}
		
		document.add(table);
		
		document.close();
	}
}

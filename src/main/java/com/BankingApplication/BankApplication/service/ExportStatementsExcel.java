package com.BankingApplication.BankApplication.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingApplication.BankApplication.dao.StatementDao;
import com.BankingApplication.BankApplication.entity.Statement;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExportStatementsExcel {

	@Autowired
	private StatementDao dao;
	
	public void exportDatExcel(long accountNumber,HttpServletResponse response) throws IOException {
		
	    List<Statement> statements=	dao.getAllStatements(accountNumber);
	    
	    HSSFWorkbook workbook=new HSSFWorkbook();
	    HSSFSheet sheet= workbook.createSheet("Account_Statements");
	    HSSFRow hssfRow= sheet.createRow(0);
	    
	    hssfRow.createCell(0).setCellValue("statementId");
	    hssfRow.createCell(1).setCellValue("amount");
	    hssfRow.createCell(2).setCellValue("transactionType");
	    hssfRow.createCell(3).setCellValue("date");
	    hssfRow.createCell(4).setCellValue("accountNumber");
	    hssfRow.createCell(5).setCellValue("accountHolderName");
	    
	    int dataRowIndex=1;
	    
	    for (Statement statement : statements) {
			HSSFRow dataRow=sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(statement.getStatementId());
			dataRow.createCell(1).setCellValue(statement.getAmount());
			dataRow.createCell(2).setCellValue(statement.getTransactionType().toString());
			dataRow.createCell(3).setCellValue(statement.getDate().toString());
			dataRow.createCell(4).setCellValue(statement.getToAccount().getAccountNumber());
			dataRow.createCell(5).setCellValue(statement.getToAccount().getAccountHolderName());
			dataRowIndex++;
		}
	    
	    ServletOutputStream outputStream=response.getOutputStream();
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.close();
	}
}

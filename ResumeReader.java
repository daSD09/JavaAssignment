package resumeReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ResumeReader {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the type of file (pdf or doc)");
		String type = sc.next();
		System.out.println("Enter the path of file");
		String path = sc.next();
		
		System.out.println("Enter the keyword to check");
		String keyWord = sc.next();
		
		boolean flag = false;
	    int count = 0;
		
		File file = new File("D:\\IRC project report\\Get_Started_With_Smallpdf.pdf");
		
		if("pdf".equalsIgnoreCase(type)) {
		
			try{
				PDDocument document = PDDocument.load(file);
				PDFTextStripper pdf= new PDFTextStripper();
				
				String data = pdf.getText(document);
				
				if(data.contains(keyWord)) {
					count = StringUtils. countMatches(data, keyWord);
					System.out.println("Selected Resume contains the required Skill");
			        System.out.println("Number of occurrences is: "+count);

				}
//				System.out.println(data);
				
			}catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}

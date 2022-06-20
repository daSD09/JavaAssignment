package question1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ResumeReader {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the type of file (pdf or doc)");
		String type = sc.next();
		String temp = sc.nextLine();
		System.out.println("Enter the path of file");
		String path = sc.nextLine();

		List<String> keyWords = new ArrayList<String>();
		keyWords.add("java");
		keyWords.add("selenium");
		keyWords.add("Capgemini");
		keyWords.add("maven");
		keyWords.add("documents");

		boolean flag = false;
		int found = 0;
		int total = 0;
		String data = "";

//F:\STS\Get_Started_With_Smallpdf.pdf
//		F:\STS\CV_SHRAMANA DAS - Copy.doc

		if ("pdf".equalsIgnoreCase(type)) {

			File file = new File(path);

			try {
				PDDocument document = PDDocument.load(file);
				PDFTextStripper pdf = new PDFTextStripper();

				data = pdf.getText(document);

			} catch (IOException e) {
				System.out.println(e);
			}
		} else if ("doc".equalsIgnoreCase(type)) {

			try {

				XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get(path)));

				XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(document);

				data = xwpfWordExtractor.getText();

			} catch (IOException e) {
				System.out.println(e);
			}

		} else {
			System.out.println("please enter valid file type");
		}

		data = data.toLowerCase();
		total = data.length();

		for (String key : keyWords) {
			key = key.toLowerCase();
			int count = 0;
			if (data.contains(key)) {
				count = StringUtils.countMatches(data, key);
				flag = true;
			}

			found += count;
		}

		if (flag) {
			System.out.println("Resume contains given KeyWords");
			double percent = (double) found / total;
			percent = percent * 100;
			System.out.println(percent + "% contains in Resume");
		} else {
			System.out.println("Resume doesn't contains the keywords");
		}
	}
}
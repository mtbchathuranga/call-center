package com.wavenet.call.center;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sample {

    public static void main(String[] args) {
        try{
            File input = new File("C:\\Users\\Administrator\\Desktop\\API Docs\\BATCH 03 B.pdf");

            PDDocument outputDocument = null;
            PDDocument inputDocument = PDDocument.loadNonSeq(input, null);
            PDFTextStripper stripper = new PDFTextStripper();
            String currentNo = null;
            for (int page = 1; page <= inputDocument.getNumberOfPages(); ++page) {
                stripper.setStartPage(page);
                stripper.setEndPage(page);
                String text = stripper.getText(inputDocument);
                Pattern p = Pattern.compile("(Message) [0-9]?[0-9]");

                // Matcher refers to the actual text where the pattern will be found
                Matcher m = p.matcher(text);
                String no = null;
                if (m.find()) {
                    no = m.group();
                }

                Pattern p2 = Pattern.compile("(F50K: Ordering Customer - Account - Name and Address)((.*(\\n|\\r|\\r\\n)){2})( {20}\\/[0-9]{10})");
                Matcher m2 = p2.matcher(text);
                String account = null;
                if (m2.find()) {
                    account = m2.group(5);
                    System.out.println("account: "+ account);
                }

                System.out.println("page: " + page + ", value: " + no);


                PDPage pdPage = (PDPage) inputDocument.getDocumentCatalog().getAllPages().get(page - 1);

                if (no != null && !no.equals(currentNo)) {
                    saveCloseCurrent(currentNo, outputDocument);
                    // create new document
                    outputDocument = new PDDocument();
                    currentNo = no;
                }
                if (no == null && currentNo == null) {
                    System.out.println("header page ??? " + page + " skipped");
                    continue;
                }
                // append page to current document
                outputDocument.importPage(pdPage);
            }
            saveCloseCurrent(currentNo, outputDocument);
            inputDocument.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void saveCloseCurrent(String currentNo, PDDocument outputDocument)
            throws IOException, COSVisitorException {
        // save to new output file
        if (currentNo != null) {
            // save document into file
            File f = new File("C:\\Users\\Administrator\\Desktop\\API Docs\\Export\\"+currentNo + ".pdf");
            if (f.exists()) {
                System.err.println("File " + f + " exists?!");
                System.exit(-1);
            }
            outputDocument.save(String.valueOf(f));
            outputDocument.close();
        }
    }
}


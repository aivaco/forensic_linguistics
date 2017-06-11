package com.forensic;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Controls all the operations that it is related to a pdf book.
 */
public class ExtractTextFromBook {
    /**
     * Basically the method is in charge of extract the text of the selected pages (sent by parameter).
     * @param book_path
     * @param start_page
     * @param end_page
     * @return
     * @throws IOException
     */
    public String extractPagesFromBook(String book_path, int start_page, int end_page) throws IOException {
        book_path = ".//hojarasca.pdf";
        start_page = 6;
        end_page = 106;
        File file = new File(book_path);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(start_page);
        pdfStripper.setEndPage(end_page);
        String text = pdfStripper.getText(document);
        document.close();
        System.out.println(text);
        return text;
    }

    /***
     * It will return only the lines that have been selected. For example: the first 20 lines.
     * @param text
     * @param lines
     * @return
     * @throws IOException
     */
    public String selectLinesFromAbove(String text,int lines)throws IOException{
        BufferedReader bufReader = new BufferedReader(new StringReader(text));
        String selected_text="";
        int count = 0;
        while( count < lines )
        {
            selected_text+=bufReader.readLine();
            ++count;
        }
        return selected_text;
    }
}

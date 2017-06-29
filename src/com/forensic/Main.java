package com.forensic;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.util.logging.Redwood;



import java.io.*;
import java.util.List;


public class Main {
    /** A logger for this class */
    //private static Redwood.RedwoodChannels log = Redwood.channels(Main.class);
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ExtractTextFromBook e = new ExtractTextFromBook();

        String sample = "";
        sample = e.extractPagesFromBook("test",2,3);
        //sample = e.selectLinesFromAbove(sample,10);

        Classify c = new Classify();
        List<TaggedWord> a = c.classifyText(sample);
        c.deleteAllOtherTypes("sp",a);

        Archivo archivo = new Archivo();
        archivo.createFile("resultado","txt");

        for (TaggedWord taggedWord : a) {
            archivo.writeInFile(taggedWord.word());
        }
    }
}

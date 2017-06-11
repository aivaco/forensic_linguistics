package com.forensic;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.logging.Redwood;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * It is in charge of classifies the words of a text.
 */
public class Classify {

    /**
     * Classifies the words of a respective text. To classify, it takes each word and assigns them an id.
     * @param text
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<TaggedWord> classifyText(String text)throws IOException, ClassNotFoundException {
        MaxentTagger tagger = new MaxentTagger(".//models/spanish.tagger");
        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
                "untokenizable=noneKeep");
        InputStream is = new ByteArrayInputStream(text.getBytes());
        BufferedReader r = new BufferedReader(new InputStreamReader(is,"utf-8"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "utf-8"));
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
        documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
        List<TaggedWord> Sentences = new ArrayList<TaggedWord>();
        for (List<HasWord> sentence : documentPreprocessor) {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            //pw.println(SentenceUtils.listToString(tSentence, false));
            Sentences.addAll(tSentence);
        }
        pw.close();
        return Sentences;
    }

    /**
     * Eliminates all the other words that are not of the desired type.
     * @param type
     * @param sentences
     * @return
     */
    public List<TaggedWord> deleteAllOtherTypes(String type, List<TaggedWord> sentences){
       //type = "pp000000";
        //log.info("Se extraerá el tipo de palabra deseado " + type);
        for (Iterator<TaggedWord>iterator = sentences.iterator(); iterator.hasNext();) {
            TaggedWord word = iterator.next();
            if(word.tag().contains("vm") || !word.tag().contains(type)){
                iterator.remove();
            }
        }
        System.out.println("Las palabras restantes son: ");

        return sentences;
    }


}

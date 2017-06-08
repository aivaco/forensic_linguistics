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

import java.io.*;
import java.util.List;

/**
 * Created by aivan on 07/06/2017.
 */
public class Classify {
    public String classifyText(String text)throws IOException, ClassNotFoundException {
        MaxentTagger tagger = new MaxentTagger(".//models/wsj-0-18-left3words-distsim.tagger");
        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
                "untokenizable=noneKeep");
        //BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(".//sample-input.txt"), "utf-8"));
        InputStream is = new ByteArrayInputStream(text.getBytes());
        BufferedReader r = new BufferedReader(new InputStreamReader(is,"utf-8"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "utf-8"));
        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
        documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
        for (List<HasWord> sentence : documentPreprocessor) {
            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
            pw.println(SentenceUtils.listToString(tSentence, false));
        }

        // print the adjectives in one more sentence. This shows how to get at words and tags in a tagged sentence.
        List<HasWord> sent = SentenceUtils.toWordList("The", "slimy", "slug", "crawled", "over", "the", "long", ",", "green", "grass", ".");
        List<TaggedWord> taggedSent = tagger.tagSentence(sent);
        String analized_text = "";
        for (TaggedWord tw : taggedSent) {
            if (tw.tag().startsWith("JJ")) {
                analized_text += tw.word();
                pw.println(tw.word());
            }
        }

        pw.close();
        return analized_text;
    }


}

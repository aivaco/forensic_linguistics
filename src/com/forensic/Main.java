package com.forensic;
import edu.stanford.nlp.util.logging.Redwood;



import java.io.*;


public class Main {
    /** A logger for this class */
    private static Redwood.RedwoodChannels log = Redwood.channels(Main.class);
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Classify c = new Classify();
        ExtractTextFromBook e = new ExtractTextFromBook();
        c.classifyText("A passenger plane has crashed shortly after take-off from Kyrgyzstan's\n" +
                "capital, Bishkek, killing a large number of those on board. The head of\n" +
                "Kyrgyzstan's civil aviation authority said that out of about 90\n" +
                "passengers and crew, only about 20 people have survived. The Itek Air\n" +
                "Boeing 737 took off bound for Mashhad, in north-eastern Iran, but turned\n" +
                "round some 10 minutes later.\n");

        e.extractPagesFromBook("test",2,3);
//        if (args.length != 2) {
//            log.info("usage: java TaggerDemo2 modelFile fileToTag");
//            return;
//        }

//        MaxentTagger tagger = new MaxentTagger(".//models/wsj-0-18-left3words-distsim.tagger");
//        TokenizerFactory<CoreLabel> ptbTokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(),
//                "untokenizable=noneKeep");
//        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(".//sample-input.txt"), "utf-8"));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "utf-8"));
//        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(r);
//        documentPreprocessor.setTokenizerFactory(ptbTokenizerFactory);
//        for (List<HasWord> sentence : documentPreprocessor) {
//            List<TaggedWord> tSentence = tagger.tagSentence(sentence);
//            pw.println(SentenceUtils.listToString(tSentence, false));
//        }
//
//        // print the adjectives in one more sentence. This shows how to get at words and tags in a tagged sentence.
//        List<HasWord> sent = SentenceUtils.toWordList("The", "slimy", "slug", "crawled", "over", "the", "long", ",", "green", "grass", ".");
//        List<TaggedWord> taggedSent = tagger.tagSentence(sent);
//        for (TaggedWord tw : taggedSent) {
//            if (tw.tag().startsWith("JJ")) {
//                pw.println(tw.word());
//            }
//        }
//
//        pw.close();
//    }
    }
}

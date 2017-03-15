/**
 * Created by vsippola on 3/14/2017.
 */

import java.io.File;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Scanner;

public class W2VTest
{
    private static Logger log = LoggerFactory.getLogger(W2VTest.class);

    //Load the pre-trained w2v net
    public static void main(String[] args) throws Exception {
        File gModel = new File("GoogleNews-vectors-negative300.bin.gz");
        log.info("Loading W2V Model....\n");
        Word2Vec vec = WordVectorSerializer.readWord2VecModel(gModel);

        // Gets Path to Text file
        //String filePath = new ClassPathResource("raw_sentences.txt").getFile().getAbsolutePath();

        //log.info("Load & Vectorize Sentences....\n");
        // Strip white space before and after for each line
        //SentenceIterator iter = new BasicLineIterator(filePath);
        // Split on white spaces in the line to get words
       // TokenizerFactory t = new DefaultTokenizerFactory();

        /*
            CommonPreprocessor will apply the following regex to each token: [\d\.:,"'\(\)\[\]|/?!;]+
            So, effectively all numbers, punctuation symbols and some special symbols are stripped off.
            Additionally it forces lower case for all tokens.
         */
       // t.setTokenPreProcessor(new CommonPreprocessor());

        log.info("Closest Words:");

        String input = "test";
        Scanner reader = new Scanner(System.in);


        while(!input.equals("exit0")){
            Collection<String> lst = vec.wordsNearest(input, 10);
            System.out.println("10 Words closest to '" + input +"': " + lst);

            System.out.println("Enter word to test (type exit0) to exit: ");
            input = reader.nextLine();
        }
    }

}

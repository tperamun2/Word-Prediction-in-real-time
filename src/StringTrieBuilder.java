import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ethan on 2016-11-16.
 * This class has a single static method that builds and returns a StringTrie
 * representing your vocabulary for text prediction. You should be able to load
 * in any plain-text file (ASCII only) to build a StringTrie. Make sure the text
 * file is in your project root directory.
 */
public class StringTrieBuilder {

    public static StringTrie load(String inputFile){
        System.out.println("Building vocabulary...");
        StringTrie result = new StringTrie();
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            while(true) {
                String line = in.readLine();
                if(line == null)
                    break;
                String[] words = line.split(" ");
                for (String s : words) {
                    result.insert(s.replaceAll("[^A-Za-z]+", ""));
                }
            }
            result.computeTotalWords();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Finished!");
        return result;
    }
}

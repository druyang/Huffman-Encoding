/**
 * @author: Andrw Yang
 */

import java.io.*;
import java.util.*;

/**
 * Frequency table methods for Huffman encoding
 */
public class FrequencyTable {
    protected HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
    protected BufferedReader input;
    private int c;

    /**
     * Constructs frequency table
     * @param pathName path of text file to tabulate
     * @throws IOException for invalid files
     */
    public FrequencyTable(String pathName) throws IOException {
        try {
            input = new BufferedReader(new FileReader(pathName));
            // loop through the file
            while ((c = input.read()) != -1) {
                char temp_letter = (char) c;
                // count the letters
                if (frequency.containsKey(temp_letter)) {
                    frequency.put(temp_letter, frequency.get(temp_letter) + 1);
                } else {
                    frequency.put(temp_letter, 1); // create new key if new character
                }
            }
        } catch (IOException e){
            System.out.println(e);
        } finally {
            input.close();
        }
    }

    public void printFrequency(){ // print for testing
        frequency.forEach((key, value) -> System.out.println(key + " : " + value ));
    }

    public HashMap<Character, Integer> getFrequency() {
        return frequency;
    }

    public static void main(String[] args) throws IOException {
        // Tested with plaintext version of wikipedia
        FrequencyTable test = new FrequencyTable("/Users/andrewyang/Documents/Github/cs10/PS3/USConstitution.txt");
        test.printFrequency();
    }

}



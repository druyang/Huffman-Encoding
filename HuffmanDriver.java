/**
 * @author: Andrw Yang
 */
import java.io.*;
import java.util.Map;


/**
 * Driver for Huffman Encoding
 */
public class HuffmanDriver {
    /**
     * Compresses a file with Huffman encoding using HuffmanEncoder
     * @param ipath path of the file
     * @return returns the tree of the Huffman compression
     * @throws IOException if there are file errors or if the file is invalid
     */
    public static HuffmanEncoder compressor(String ipath) throws IOException {
        int i;
        String opath = ipath.substring(0,ipath.length()-4) + "_compressed.txt"; // rename
        BufferedReader input = new BufferedReader(new FileReader(ipath)); // reads file
        BufferedBitWriter output = new BufferedBitWriter(opath); // output file

        HuffmanEncoder tree = new HuffmanEncoder(ipath);
        Map<Character, String> code_map = tree.codenRetriever();

        // Loop through the entire input string
        while ((i = input.read()) != -1){
            Character c = (char) i;
            String out_string = code_map.get(c);
            // Output to the file the value of the input character's bit
            for(Character x : out_string.toCharArray()){
                if (x == '1'){ output.writeBit(true); }
                else { output.writeBit(false); }
            }
        }
        input.close();
        output.close();
        // Returns tree for decompression
        return tree;
    }

    /**
     * Decompresses a huffman encoded file
     * @param ipath path of the compressed file
     * @param true_tree Huffman tree of the compressed file
     * @throws IOException for invalid files
     */
    public static void decompressor(String ipath, HuffmanEncoder true_tree) throws IOException{
        String opath = ipath.substring(0,ipath.length()-15) + "_decompressed.txt";
        BufferedBitReader input = new BufferedBitReader(ipath); //
        BufferedWriter output = new BufferedWriter(new FileWriter(opath));

        BinaryTree<HuffmanUnit> code_tree = true_tree.getFinal_tree();


        while (input.hasNext()) { // Loop through the bits
            boolean b = input.readBit();
            // Navigate the tree depending on the bits
            if (code_tree.isLeaf()) {
                output.write(code_tree.getData().getC());
                code_tree = true_tree.getFinal_tree();
            }
            // Traverse left or right if the bit is true or false
            if (!b) {
                code_tree = code_tree.getLeft();
            } else if (b) {
                code_tree = code_tree.getRight();
            }
        }

        // Closing the files
        input.close();
        output.close();
    }

    public static void main(String[] args) {
        try {
            decompressor("/Users/andrewyang/Documents/Github/cs10/PS3/tests/enwik8_compressed.txt", compressor("/Users/andrewyang/Documents/Github/cs10/PS3/tests/enwik8.txt"));
        } catch (FileNotFoundException e){
            System.out.println("File error:" + e);
        } catch (NullPointerException e){
            System.out.println("File error:" + e);
        } catch (Exception e){
            System.out.println(e.getMessage() + e.getStackTrace());
        }
    }
}

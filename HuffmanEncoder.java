/**
 * @author: Andrw Yang
 */
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Contains methods for maps and trees needed for Huffman compression
 */
public class HuffmanEncoder {
    private BinaryTree<HuffmanUnit> final_tree;
    private Map<Character, String> code_map = new HashMap<>();

    /**
     * Creates Huffman Encoding tree from file path
     * @param file_name     String path of file
     * @throws IOException  Invalid file
     */
    public HuffmanEncoder(String file_name) throws IOException {
        FrequencyTable table = new FrequencyTable(file_name); // Creates frequency table for the file

        // Instantiates a new priority queue of single objects using an anonymous comparator
        PriorityQueue<BinaryTree<HuffmanUnit>> priority_trees = new PriorityQueue<>(table.getFrequency().size(), new Comparator<BinaryTree<HuffmanUnit>>() {
            @Override
            public int compare(BinaryTree<HuffmanUnit> o1, BinaryTree<HuffmanUnit> o2) {
                return Integer.compare(o1.getData().getF(), o2.getData().getF());
            }
        });

        // Adds each character k from the frequency table into the queue
        for (Character k : table.getFrequency().keySet()) {
            priority_trees.add(new BinaryTree<HuffmanUnit>(new HuffmanUnit(k, table.getFrequency().get(k))));
        }

        /*
        Testing the queue by printing out frequencies
        while(!priority_trees.isEmpty()){
            HuffmanUnit test = priority_trees.poll().getData();
            System.out.println(test.getF() + " : " + test.getC());
        }*/

        // Tree Creation
        while (priority_trees.size() > 1){
            BinaryTree<HuffmanUnit> left = priority_trees.poll();
            BinaryTree<HuffmanUnit> right = priority_trees.poll();
            BinaryTree<HuffmanUnit> new_tree = new BinaryTree<HuffmanUnit>(new HuffmanUnit(left.getData().getF() + left.getData().getF()), left, right);
            priority_trees.add(new_tree);
        }

        // Handle exception if it is just one character
        if (priority_trees.size() == 1){
            BinaryTree<HuffmanUnit> vine = priority_trees.poll();
            BinaryTree vine_node = new BinaryTree(new HuffmanUnit(null, vine.getData().getF()), vine, null);
            priority_trees.add(vine_node);
        } else if (priority_trees.size() == 0) { throw new IOException("File does not contain anything!");}

        // Last element left in the priority queue is our final tree for compression/decompression
        final_tree = priority_trees.remove();
        // printTree(final_tree); //Post order traversal of the tree for testing
    }

    public BinaryTree<HuffmanUnit> getFinal_tree() {
        return final_tree;
    }

    /**
     * Generates code map for compression, along with helper method
     */
    protected Map<Character, String> codenRetriever(){
        String sofar = ""; // keeps track of path
        codenRetrieverHelper(final_tree, sofar);
        // System.out.println(code_map);
        return code_map;
    }

    private void codenRetrieverHelper(BinaryTree<HuffmanUnit> current, String sofar){
        String next_code; // path of another branch as you recurse through the tree
        // Traverse the tree
        if (current.hasRight()){
            next_code = sofar + "1";
            codenRetrieverHelper(current.getRight(), next_code);
        }
        if (current.hasLeft()){
            next_code = sofar + "0";
            codenRetrieverHelper(current.getLeft(), next_code);
        }
        // Hit the bottom of the tree
        if (current.isLeaf()) {
            code_map.put(current.getData().getC(), sofar);
        }
    }

    /**
     * Prints tree for testing purposes
     * @param tree Huffman tree
     */
    private static void printTree(BinaryTree<HuffmanUnit> tree){
        if (tree == null) return;
        // Recurse through children
        printTree(tree.getLeft());
        printTree(tree.getRight());
        System.out.print(tree.getData().getF() + " ");

    }
}

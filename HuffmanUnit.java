public class HuffmanUnit {
    private int f;
    private Character c;

    // Constructor for initial priority queue
    public HuffmanUnit(Character c, int f){
        this.c = c;
        this.f = f;
    }

    // Constructor for frequency tree
    public HuffmanUnit(int f){
        this.f = f;
        this.c = null;
    }

    public void setC(char c) {
        this.c = c;
    }

    public void setF(int f) {
        this.f = f;
    }

    public char getC() {
        return c;
    }

    public int getF() {
        return f;
    }
}

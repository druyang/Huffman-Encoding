# Huffman Encoding Documentation

-   Andrw Yang

# Testing

The following documents testing of the assignment’s individual components.

## Frequency Table

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-2.png)

Printed the frequency table to ensure all character values were counted correctly.

## Priority Queue

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-1.png)

Printed the priority queue to ensure that the least frequent characters were listed first.

## Huffman Tree

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-3.png)  
Tested the Huffman tree by printing the tree in a **post order traversal**. While this isn’t the greatest visualization, the largest parent node will be on the far right, as exhibited here.

# Test and Boundary Cases

I tested the files using the **Artificial Corpus**, **Wikipedia**, and **empty/invalid files** to cover boundary cases. The following were covered

-   Single character, high repitition (aaa.txt)
-   Single pattern, high repitition (alphabet.txt)
-   No pattern, random (random.txt)
-   Large files (enwik8.txt)
-   Unorthodox characters (enwik8.txt)
-   Empty file (empty.txt, pictured below)
-   Invalid file (folder in another directory, not pictured)
-   Invalid file type (.exe file, not pictured)

The `try` and `catch` statements are able to handle all thrown IO, File, and Null Pointer errors.

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-6.png)

## Empty File

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-7.png)Testing an empty file. Exception `File error` thrown. I later edited the code to catch other types of exceptions.

## Large Files and Unorthodox Characters

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-4.png)

## Patterned Files

![enter image description here](https://andrwyang.com/piclib/COSC10-PS3-5.png)

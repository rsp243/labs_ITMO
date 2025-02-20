package task2;

public class Task2 {
    public static void solveTask() {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.err.println(tree.inOrder()); // Output: 10 20 25 30 40 50
    }
}

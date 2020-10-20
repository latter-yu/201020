import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class Main {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 输入两棵二叉树 A，B，判断 B 是不是 A 的子结构。
        // ps：我们约定空树不是任意一个树的子结构

        boolean ret = false;
        if (root2 != null && root1 != null) {
            if (root1.val == root2.val) {
                // 判断根节点下有没有子结构
                ret = isSubtree(root1, root2);
            }
            if (ret == false) {
                // 根节点下没有，判断左子树下有没有子结构
                ret = HasSubtree(root1.left, root2);
            }
            if (ret == false) {
                // 根节点和左子树都没有，判断右子树下有没有子结构
                ret = HasSubtree(root1.right, root2);
            }
        }
        return ret;
    }
    public boolean isSubtree(TreeNode root1,TreeNode root2) {
        // 子树的意思是包含了一个节点，就得包含这个节点下的所有节点。
        // 子结构的意思是包含了一个节点，可以只取左子树或者右子树，或者都不取。
        if (root1 == null && root2 != null) {
            return false;
        }
        if (root2 == null) {
            // 已经有根节点相同的前提了
            return true;
        }
        if (root1.val != root2.val) {
            // 需先判空再确定值是否相等，否则会有空指针异常
            return false;
        }
        return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
    }
}

class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        // 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
        // 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

        ArrayList<Integer> list = new ArrayList<> ();
        if (matrix.length == 0) {
            return list;
        }
        int xStart = 0;
        int xEnd = matrix[0].length - 1;
        int yStart = 0;
        int yEnd = matrix.length - 1;
        while (xStart <= xEnd && yStart <= yEnd) {
            if (xStart <= xEnd) {
                for (int i = xStart; i <= xEnd; i++) {
                    list.add(matrix[yStart][i]);
                }
            }
            if (yStart < yEnd) {
                for (int j = yStart + 1; j <= yEnd; j++) {
                    list.add(matrix[j][xEnd]);
                }
            }
            if (xStart < xEnd && yStart < yEnd) {
                for (int i = xEnd - 1; i >= xStart; i--) {
                    list.add(matrix[yEnd][i]);
                }
            }
            if (xStart < xEnd && yStart < yEnd) {
                for (int j = yEnd - 1; j > yStart; j--) {
                    list.add(matrix[j][xStart]);
                }
            }
            xStart++;
            xEnd--;
            yStart++;
            yEnd--;
        }
        return list;
    }

    public boolean VerifySquenceOfBST(int [] sequence) {
        // 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
        // 如果是则返回 true,否则返回 false。假设输入的数组的任意两个数字都互不相同

        int[] arr = sequence.clone();
        Arrays.sort(arr); // 排序后为中序遍历结果
        // 对于栈，若输出为后序遍历结果（左右中），则栈的压入应是中序遍历结果（左中右）
        return ispopOrder(arr, sequence);
    }
    public boolean ispopOrder(int[] arr, int[] sequence) {
        Stack<Integer> stack = new Stack<>();
        if (arr.length == 0 || sequence.length == 0) {
            return false;
        }
        int popIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
            while (!stack.isEmpty() && stack.peek() == sequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        // 输入 n 个整数，找出其中最小的 K 个数。
        // 例如输入 4, 5, 1, 6, 2, 7, 3, 8 这 8 个数字
        // 则最小的 4 个数字是 1, 2, 3, 4。

        ArrayList<Integer> list = new ArrayList<>();
        int len = input.length;
        if (len < k) {
            return list;
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = len - 1; j > i; j--) {
                if (input[j - 1] > input[j]) {
                    int tmp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = tmp;
                }
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }
}
package Code;

import java.util.*;
import java.io.*;

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data=data;
    }
}

class BinaryTree{
    Node root;

    void createNode(int rootData, int leftData, int rightData){
        if(root==null){
            root=new Node(rootData);

            if(leftData!=-1){
                root.left=new Node(leftData);
            }
            if(rightData!=-1){
                root.right=new Node(rightData);
            }
        }else{
            searchNode(root, rootData, leftData, rightData);
        }
    }

    void searchNode(Node node, int rootData, int leftData, int rightData){
        if(node==null){
            return;
        }else if(node.data==rootData){
            if(leftData!=-1){
                root.left=new Node(leftData);
            }
            if(rightData!=-1){
                root.right=new Node(rightData);
            }
        }else{
            searchNode(node.left, rootData, leftData, rightData);
            searchNode(node.right, rootData, leftData, rightData);
        }
    }

    void preOrder(Node node){
        if(node!=null){
            System.out.println(node.data);
            if(node.left!=null){
                preOrder(node.left);
            }
            if(node.right!=null){
                preOrder(node.right);
            }
        }
    }

    void inOrder(Node node){
        if(node!=null){
            if(node.left!=null){
                preOrder(node.left);
            }
            System.out.println(node.data);
            if(node.right!=null){
                preOrder(node.right);
            }
        }
    }

    void postOrder(Node node){
        if(node!=null){
            if(node.left!=null){
                preOrder(node.left);
            }
            System.out.println(node.data);
            if(node.right!=null){
                preOrder(node.right);
            }
        }
    }

    void levelOrder(){
        this.levelOrder(root);
    }

    void levelOrder(Node node){
        Queue<Node> q=new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            Node current=q.poll();

            System.out.println(current.data+" ");

            if(current.left!=null){
                q.add(current.left);
            }
            if(current.left!=null){
                q.add(current.right);
            }
        }
    }
}

class BOJ_9934{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9934;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int K=Integer.parseInt(br.readLine());

        int[] weights=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        BinaryTree binaryTree=new BinaryTree();


        int[] temp=new int[3];

        br.close();
    }
}
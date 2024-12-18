package Code;

import java.util.*;
import java.io.*;

class Node1991{
    char data;

    Node1991 left;
    Node1991 right;

    public Node1991(char data){
        this.data=data;
    }
}

class Tree{
    Node1991 root;
    StringBuilder sb=new StringBuilder();

    public void refreshSB(){
        this.sb=new StringBuilder();
    }

    public void insertNode(char headData, char leftData, char rightData){
        if(this.root==null){
            this.root=new Node1991(headData);

            if(leftData!='.'){
                this.root.left=new Node1991(leftData);
            }

            if(rightData!='.'){
                this.root.right=new Node1991(rightData);
            }
        }else{
            searchNode(this.root, headData, leftData, rightData);
        }
    }

    public void searchNode(Node1991 node, char headData, char leftData, char rightData){
        if(node==null){
            return;
        }else{
            if(node.data==headData){
                if(leftData!='.'){
                    node.left=new Node1991(leftData);
                }
    
                if(rightData!='.'){
                    node.right=new Node1991(rightData);
                }
            }else{
                if(node.left!=null) searchNode(node.left, headData, leftData, rightData);
                if(node.right!=null) searchNode(node.right, headData, leftData, rightData);
            }
        }
    }

    public void preOrder(Node1991 node){
        if(node==null) return;

        sb.append(node.data);
        if(node.left!=null) preOrder(node.left);
        if(node.right!=null) preOrder(node.right);
    }

    public void inOrder(Node1991 node){
        if(node==null) return;

        if(node.left!=null) inOrder(node.left);
        sb.append(node.data);
        if(node.right!=null) inOrder(node.right);
    }

    public void postOrder(Node1991 node){
        if(node==null) return;

        if(node.left!=null) postOrder(node.left);
        if(node.right!=null) postOrder(node.right);
        sb.append(node.data);
    }
}
class BOJ_1991{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1991;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        Tree tree=new Tree();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            tree.insertNode(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
        }

        tree.preOrder(tree.root);
        System.out.println(tree.sb);

        tree.refreshSB();
        tree.inOrder(tree.root);
        System.out.println(tree.sb);

        tree.refreshSB();
        tree.postOrder(tree.root);
        System.out.println(tree.sb);

        br.close();
    }
}
package Code;

import java.util.*;
import java.io.*;

class Node{
    String data;
    Node left;
    Node right;

    public Node(String data){
        this.data=data;
    }
}

class BinaryTree{
    Node root;

    void createNode(String[] data){ 
        for(int i=1;i<Integer.parseInt(data[0]);i++){
            if(root==null){
                root=new Node("*");
    
                if(root.left==null){
                    root.left=new Node(data[i]);
                }
                else if(root.right==null){ // 사전 순서가 앞서는 게 왼쪽으로 오도록 스와핑
                    if((int)root.left.data.charAt(0)>(int)data[i].charAt(0)){
                        root.right.data=root.left.data;
                        root.left.data=data[i];
                    }else{
                        root.right=new Node(data[i]);
                    }
                }
            }else{
                searchNode(root, data[1], data);
            }
        }
    }

    void searchNode(Node node, String rootData, String[] data){
        if(node==null){
            return;
        }else if(node.data==rootData){
            for(int i=1;i<Integer.parseInt(data[0]);i++){
                if(root.left==null){
                    root.left=new Node(data[i]);
                }
                else if(root.right==null){ // 사전 순서가 앞서는 게 왼쪽으로 오도록 스와핑
                    if((int)root.left.data.charAt(0)>(int)data[i].charAt(0)){
                        root.right.data=root.left.data;
                        root.left.data=data[i];
                    }else{
                        root.right=new Node(data[i]);
                    }
                }
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

class BOJ_14725{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14725;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());

        BinaryTree binaryTree=new BinaryTree();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int infoLength=Integer.parseInt(st.nextToken());

            String[] feeds=new String[infoLength];

            for(int j=0;j<infoLength;j++){
                feeds[j]=st.nextToken();
            }

            binaryTree.createNode(feeds);
        }

        binaryTree.levelOrder();

        br.close();
    }
}
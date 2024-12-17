package Code;

import java.util.*;
import java.io.*;

class Node{
    HashMap<String, Node> child;
    boolean endOfWord;

    Node(){
        this.child=new HashMap<>();
    }
}

class Trie{
    Node root;

    Trie(){
        this.root=new Node();
    }

    public void insert(String[] strs){
        Node node=this.root;

        for(int i=0;i<strs.length;i++){
            String str=strs[i];

            node.child.putIfAbsent(str, new Node());
            node=node.child.get(str);
        }

        node.endOfWord=true;
    }

    public void printAllChilds(){
        printAllChilds(this.root, 0);
    }
    
    private void printAllChilds(Node node, int depth){
        List<String> keySet=new ArrayList<>(node.child.keySet());

        Collections.sort(keySet);

        for(String key:keySet){
            System.out.println("--".repeat(depth)+key);
            printAllChilds(node.child.get(key), depth+1);
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

        Trie trie=new Trie();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int infoLength=Integer.parseInt(st.nextToken());

            String[] inputs=new String[infoLength];

            for(int j=0;j<infoLength;j++){
                inputs[j]=st.nextToken();
            }

            // System.out.println("trie에 다음의 Input을 삽입합니다:"+Arrays.toString(inputs));
            trie.insert(inputs);
        }

        trie.printAllChilds();

        br.close();
    }
}
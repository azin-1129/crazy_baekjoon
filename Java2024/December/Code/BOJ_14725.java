package Code;

import java.util.*;
import java.io.*;

class Node{
    HashMap<Character, Node> child;
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

    public void insert(String str){
        Node node=this.root;

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);

            node.child.putIfAbsent(c, new Node());
            node=node.child.get(c);
        }
    }

    boolean search(String str){
        Node node=this.root;

        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);

            if(node.child.containsKey(c)){
                node=node.child.get(c);
            }else{
                return false;
            }
        }

        return node.endOfWord;
    }

    public boolean delete(String str){
        return this.delete(this.root, str, 0);
    }

    boolean delete(Node node, String str, int idx){
        char c=str.charAt(idx);

        if(!node.child.containsKey(c)){
            return false;
        }

        Node current=node.child.get(c);
        idx++;

        if(idx==str.length()){
            if(!current.endOfWord){
                return false;
            }

            current.endOfWord=false;

            if(current.child.isEmpty()){
                node.child.remove(c);
            }
        }else{
            if(!this.delete(current, str, idx)){
                return false;
            }

            if(!current.endOfWord && current.child.isEmpty()){
                node.child.remove(c);
            }
        }

        return true;
    }
}

class BOJ_14725{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14725;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        ArrayList<String> keys=new ArrayList<>();

        int N=Integer.parseInt(br.readLine());

        Trie trie=new Trie();

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            int infoLength=Integer.parseInt(st.nextToken());

            String trieInput="";

            for(int j=1;j<infoLength;j++){
                trieInput+=st.nextToken();
            }
            trie.insert(trieInput);
        }

        Collections.sort(keys);

        trie.root.child.forEach((key,value)->{
            while(value.endOfWord!=true){
                System.out.println(key+":"+value);
            }
        });

        br.close();
    }
}
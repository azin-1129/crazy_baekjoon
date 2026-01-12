package Code;

import java.util.*;
import java.io.*;

class BOJ_5052 {
    static class Node{
        HashMap<Character, Node> child;
        boolean EOW;

        Node(){
            this.child=new HashMap<>();
            this.EOW=false;
        }
    }

    static class Trie{
        Node root;

        Trie(){
            this.root=new Node();
        }

        boolean insert(String str){
            Node node=root;
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);
                if(node.child.containsKey(ch)){
                    node=node.child.get(ch);
                    // insert 단어에 다른 단어의 EOW가 포함된다면 false
                    if(node.EOW==true){
                        return false;
                    }
                }else{
                    node.child.put(ch, new Node());
                    node=node.child.get(ch);
                }
            }

            // insert 단어의 EOW가 다른 단어에 포함된다면 false
            if(node.child.size()==0){
                node.EOW=true;
                return true;
            }else{
                return false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=5052;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int t=0;t<T;t++){
            Trie trie=new Trie();
            int N=Integer.parseInt(br.readLine());
            boolean flag=false;
            for(int i=0;i<N;i++){
                if(flag){
                    br.readLine();
                    continue;
                }
                String tell=br.readLine();
                if(!trie.insert(tell)){
                    flag=true;
                }
            }
            if(flag){
                sb.append("NO\n");
            }else{
                sb.append("YES\n");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}
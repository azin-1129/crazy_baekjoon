package Code;

import java.util.*;
import java.io.*;

// 참고 : https://innovation123.tistory.com/116
public class Trie_practice {
    static class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;

        Node(){
            this.child=new HashMap<>();
            this.endOfWord=false;
        }
    }
    static class Trie {
        Node root;

        public Trie(){
            this.root=new Node();
        }

        void insert(String str){
            Node node=this.root;

            for(int i=0;i<str.length();i++){
                Character c=str.charAt(i);
                node.child.putIfAbsent(c, new Node()); // 자식이 있는지 체크
                node=node.child.get(c); // 자식으로 이동
            }
            node.endOfWord=true;
        }
        
        boolean search(String str){
            Node node=this.root;

            for(int i=0;i<str.length();i++){
                Character c=str.charAt(i);
                if(node.child.containsKey(c)){
                    node=node.child.get(c);
                }else{
                    return false;
                }
            }

            return node.endOfWord;
        }

        boolean delete(String str){
            return delete(this.root, str, 0);
        }

        private boolean delete(Node node, String str, int idx){
            Character c=str.charAt(idx);

            if(!node.child.containsKey(c)){
                return false;
            }

            Node current=node.child.get(c);
            idx++;
            if(idx==str.length()){
                if(!current.endOfWord){
                    return false; // 지우려는 단어가 존재하지 않는다.
                }else{
                    current.endOfWord=false; // EOW만 지우기
                }
            }else{
                if(!this.delete(current, str, idx)){ // 더이상 재귀 불가능
                    return false;
                }
            }

            return true;
        }
    }
    public static void main(String[] args) throws Exception {
        
    }    
}

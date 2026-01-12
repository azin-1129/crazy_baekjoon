package Code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class BOJ_14426 {
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

        void insert(String str){
            Node node=root;
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);
                if(node.child.containsKey(ch)){
                    node=node.child.get(ch); // ch이 이미 존재하여 다음으로 넘어간다.
                }else{
                    node.child.put(ch, new Node());
                    node=node.child.get(ch); // 새로 원소를 생성하여 넘어간다.
                }
            }
            node.EOW=true;
        }

        // true: 접두사다, false: 접두사가 아니다
        boolean find(String str){
            Node node=root;
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);
                if(node.child.containsKey(ch)){
                    node=node.child.get(ch); // ch이 이미 존재하여 다음으로 넘어간다.
                }else{
                    return false; // 단어가 없다.
                }
            }
            return true;
        }

        boolean delete(String str){
            Node node=root;
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);
                if(node.child.containsKey(ch)){
                    node=node.child.get(ch); // ch이 이미 존재하여 다음으로 넘어간다.
                }else{
                    return false; // 단어가 존재하지 않아, 삭제 처리를 하지 않았다.
                }
            }
            node.EOW=false; // 단어 제거 처리
            return true;
        }
    }
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 14426;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        Trie trie=new Trie();
        for(int i=0;i<N;i++){ // 문자열 입력
            trie.insert(br.readLine());
        }

        int result=0;
        for(int i=0;i<M;i++){ // 검사해야 하는 문자열
            if(trie.find(br.readLine())){
                result++;
            }
        }

        // 검사해야 하는 문자열이, 입력된 문자열 중 포함되어 있는가?
        System.out.println(result);
        br.close();
    }
}

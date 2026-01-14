package Code;

import java.util.*;
import java.io.*;

class BOJ_16934 {
    static class Node{
        HashMap<Character, Node> child;
        String prefix;
        boolean EOW; // 필요한가?

        Node(){
            this.child=new HashMap<>();
            this.prefix="";
            this.EOW=false;
        }
    }
    static class Trie{
        Node root;

        Trie(){
            this.root=new Node();
        }

        String insert(String str){
            // System.out.println("insert : "+str);
            Node node=root;
            String prefix="";
            String prefixTemp="";
            boolean isNew=false;
            for(int i=0;i<str.length();i++){
                char ch=str.charAt(i);

                prefixTemp+=ch;

                if(node.child.containsKey(ch)){
                    node=node.child.get(ch);
                }else{
                    if(!isNew){ // 접두사 선정 필요
                        // System.out.println("접두사를 선정합니다:"+prefixTemp);
                        // 접두사 선정
                        prefix=prefixTemp;
                        node.prefix=prefix;
                        prefixCount.put(prefix, 1);
                        // System.out.println(prefix+":1");
                        isNew=true;
                        // 노드 작업
                        node.child.put(ch, new Node());
                        node=node.child.get(ch);
                    }else{ // 이미 접두사가 선정됨. 즉 노드 작업만
                        node.child.put(ch, new Node());
                        node=node.child.get(ch);
                    }
                }
            }

            // 만약 접두사 선정을 못했다면, nickname+n
            if(!isNew){
                // System.out.println("접두사를 선정하지 못했습니다.");
                if(!nicknames.containsKey(prefixTemp)){
                    // 접두사 hm<String, Integer>에 key가 없다면
                    // hm.put(접두사, 1)
                    node.prefix=prefixTemp;
                    nicknames.put(prefixTemp, 1);
                    return prefixTemp;
                }else{
                    // int count=hm.get(접두사)+1;
                    // hm.put(접두사, count);
                    int count=nicknames.get(prefixTemp)+1;
                    node.prefix=prefixTemp+count;
                    nicknames.put(prefixTemp, count); // 정보 갱신
                    // System.out.println(prefixTemp+":"+count);

                    return prefixTemp+count;
                }
            }else{ // 닉 정보 저장
                nicknames.put(prefixTemp, 1);
            }

            // System.out.println("최종 접두사:"+prefix);
            return prefix;
        }
    }
    static HashMap<String, Integer> nicknames=new HashMap<>();
    static HashMap<String, Integer> prefixCount=new HashMap<>();
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=16934;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        Trie trie=new Trie();
        StringBuilder sb=new StringBuilder();
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String nickname=br.readLine();
            sb.append(trie.insert(nickname)+"\n");
        }

        // System.out.println();
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}
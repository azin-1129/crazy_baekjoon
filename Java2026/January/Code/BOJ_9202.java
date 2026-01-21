package Code;

import java.util.*;
import java.io.*;

class BOJ_9202 {
    static class Node{
        HashMap<Character, Node> child;

        Node(){
            this.child=new HashMap<>();
        }
    }
    static class Trie{
        Node root;

        Trie(){
            this.root=new Node();
        }

        boolean search(String str){
            int stringLength=str.length();
            Node node=root;
            for(int i=0;i<stringLength;i++){
                char ch=str.charAt(i);
                if(node.child.containsKey(ch)){
                    node=node.child.get(ch);
                }else{
                    return false;
                }
            }

            // 1~2글자: 0점
            // 3~4글자: 1점
            // 5글자: 2점
            // 6글자: 3점
            // 7글자: 5점
            // 8글자: 11점
            switch(stringLength){
                case 3:
                case 4:
                    boardScore+=1;
                    break;
                case 5:
                    boardScore+=2;
                    break;
                case 6:
                    boardScore+=3;
                    break;
                case 7:
                    boardScore+=5;
                    break;
                case 8:
                    boardScore+=11;
                    break;
            }
            if(maxWordLength<stringLength){
                maxWordLength=stringLength;
                // System.out.println(str+"을 가장 긴 단어로 설정합니다.");
                longestWord=str; // 가장 긴 단어 설정
            }
            foundWordCount++; // 찾은 단어 개수 증가
            return true;
        }
    }
    static int[] dx={0, -1, 1, 0, 0, -1, -1,  1, 1};
    static int[] dy={0,  0, 0, -1, 1, -1,  1, -1, 1};
    static boolean[][] visited;
    static char[][] board;
    static int N=4;
    static int boardScore; // 보드 최대 점수
    static String longestWord; // 가장 긴 단어
    static int maxWordLength;
    static int foundWordCount; // 찾은 단어 개수
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 9202;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        // 단어 사전 입력 받고 나서(찾을 str들), 정렬해야 함.
        // 정렬 기준: 길이는 상관 X, 사전 상 앞서는 순.
        int w=Integer.parseInt(br.readLine());
        String[] words=new String[w];
        for(int i=0;i<w;i++){
            words[i]=br.readLine();
        }
        Arrays.sort(words);

        br.readLine(); // 엔터 스킵

        // 보드 하나가 완성되면, 그 보드 데이터로 8방 dfs
        StringBuilder sb=new StringBuilder();
        int b=Integer.parseInt(br.readLine());
        for(int i=0;i<b;i++){
            Trie trie=new Trie();
            board=new char[4][4];

            // 정적 변수 초기화
            boardScore=0;
            longestWord="";
            maxWordLength=0;
            foundWordCount=0;
            for(int x=0;x<4;x++){
                String str=br.readLine();
                for(int y=0;y<4;y++){
                    board[x][y]=str.charAt(y);
                }
            }

            for(int x=0;x<4;x++){
                for(int y=0;y<4;y++){
                    visited=new boolean[N][N];
                    // 시작점은 여기서 처리
                    visited[x][y]=true;
                    trie.root.child.putIfAbsent(board[x][y], new Node());
                    dfs(x, y, trie.root.child.get(board[x][y]), 1, ""+board[x][y]);
                    visited[x][y]=false;
                }
            }
            for(int j=0;j<w;j++){
                // System.out.println(words[j]+" 단어를 검색합니다...");
                if(trie.search(words[j])){
                    // System.out.println("단어를 찾았어요.");
                    // System.out.println("최장길이 단어 정보:"+longestWord);
                }else{
                    // System.out.println("단어를 찾지 못했습니다.");
                }
            }
            sb.append(String.format("%d %s %d\n", boardScore, longestWord, foundWordCount));
            br.readLine(); // 엔터 스킵
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    // 8방 탐색이 제대로 수행되지 않고 있음. GCPC 발견 불가
    static void dfs(int x, int y, Node node, int depth, String status){
        if(depth==8){
            return;
        }

        for(int d=1;d<=8;d++){
            int nx=x+dx[d];
            int ny=y+dy[d];

            if(outOfRange(nx, ny)){
                continue;
            }
            if(visited[nx][ny]){ // 한 DFS에서, 중복된 노드로 돌아가기 X
                continue;
            }

            node.child.putIfAbsent(board[nx][ny], new Node());
            visited[nx][ny]=true;
            dfs(nx, ny, node.child.get(board[nx][ny]), depth+1, status+board[nx][ny]);
            visited[nx][ny]=false;
        }
    }
    static boolean outOfRange(int x, int y){
        if(x<0 || y<0 || x>=N || y>=N){
            return true;
        }
        return false;
    }
}
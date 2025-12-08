package Code;

import java.util.*;
import java.io.*;

class BOJ_2250 {
    static class Node{
        int left;
        int right;

        public Node(int left, int right){
            this.left=left;
            this.right=right;
        }
    }
    static List<Node>[] list;
    // list[] 자체는 레벨, list[]의 원소는 레벨에 속한 노드들의 열 번호
    static List<Integer>[] nodeChart;
    static int depth=1, col=0;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=2250;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        int n=Integer.parseInt(br.readLine());
        list=new ArrayList[n+1];
        nodeChart=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            list[i]=new ArrayList<>();
            nodeChart[i]=new ArrayList<>();
        }

        int[] ranks=new int[n+1];
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int mid=Integer.parseInt(st.nextToken());
            int left=Integer.parseInt(st.nextToken());
            int right=Integer.parseInt(st.nextToken());
            
            if(left!=-1){
                ranks[left]++;
            }
            if(right!=-1){
                ranks[right]++;
            }
            list[mid].add(new Node(left, right));
        }

        int root=-1;
        for(int i=1; i<=n;i++){
            if(ranks[i]==0){
                root=i;
                break;
            }
        }

        inOrder(root);
        System.out.println(Arrays.toString(nodeChart));
        // 너비 계산
        int max=-1, level=0;
        for(int i=1;i<=n;i++){
            int len=nodeChart[i].size();
            if(len>0){
                int s=nodeChart[i].get(0);
                int e=nodeChart[i].get(len-1);
                int width=e-s+1;

                if(max<width){
                    max=width;
                    level=i;
                }
            }
        }
        System.out.println(level+" "+max);
        br.close();
    }
    static void inOrder(int node){
        for(Node next:list[node]){
            if(next.left!=-1){
                depth++;
                inOrder(next.left);
            }
            col++; // 중위순회 탐색 순서(column number)
            nodeChart[depth].add(col);
            if(next.right!=-1){
                depth++;
                inOrder(next.right);
            }
            depth--;
        }
    }
}
package Code;

import java.util.*;
import java.io.*;

class BOJ_13418 {
    static class Node{
        int now;
        int next;
        int isUpper;

        Node(int now, int next, int isUpper){
            this.now=now;
            this.next=next;
            this.isUpper=isUpper;
        }

        @Override
        public String toString(){
            return "[now:"+this.now+", next:"+this.next+", isUpper="+this.isUpper+"]";
        }
    }
    static int[] parents;
    static int lowWeight, highWeight, N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=13418;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken()); // 건물의 수

        int M=Integer.parseInt(st.nextToken()); // 도로의 수
                PriorityQueue<Node> lowPq=new PriorityQueue<>(
            new Comparator<Node>(){
                @Override
                public int compare(Node node1, Node node2){
                    return Integer.compare(node1.isUpper, node2.isUpper);
                }
            }
        );
        PriorityQueue<Node> highPq=new PriorityQueue<>(
            new Comparator<Node>(){
                @Override
                public int compare(Node node1, Node node2){
                    return Integer.compare(node2.isUpper, node1.isUpper);
                }
            }
        );
        for(int i=0;i<=M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int isUpper=Integer.parseInt(st.nextToken())==0?1:0;
            Node node=new Node(from, to, isUpper);
            // System.out.println("정보 추가중:"+node);
            lowPq.offer(node);
            highPq.offer(node);
        }

        // 시작: 항상 0

        // 각 MST 완성 후, 총 오르막길 개수**2가 피로도
        // 답 : 제일 피곤한 경로의 피로도-제일 덜 피곤한 경로의 피로도
        System.out.println(topologySort(highPq)-topologySort(lowPq));
        br.close();
    }
    static int topologySort(PriorityQueue<Node> pq){
        int upperCount=0;
        int count=0; // 포함한 간선 수
        parents=new int[N+1];
        for(int i=1;i<=N;i++){
            parents[i]=i;
        }
        while(!pq.isEmpty()){
            if(count==(N)){
                break;
            }
            Node current=pq.poll();
            // System.out.println(current);
            int now=current.now;
            int next=current.next;
            int isUpper=current.isUpper;
            if(union(now, next)){
                // System.out.println("이 간선을 포함합니다.");
                upperCount+=isUpper;
                count++;
            }
        }

        // System.out.println("오르막길 수:"+upperCount);
        return upperCount*upperCount;
    }
    static boolean union(int v1, int v2){
        int parentV1=find(v1);
        int parentV2=find(v2);

        if(parentV1<parentV2){
            parents[parentV2]=parentV1;
            return true;
        }else if(parentV1==parentV2){
            return false;
        }else{
            parents[parentV1]=parentV2;
            return true;
        }
    }
    static int find(int v){
        if(parents[v]!=v){
            return parents[v]=find(parents[v]);
        }

        return v;
    }
}
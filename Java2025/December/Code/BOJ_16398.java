package Code;

import java.util.*;
import java.io.*;

class BOJ_16398 {
    static class Node implements Comparable<Node>{
        int from;
        int to;
        long weight;

        Node(int from, int to, long weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Node node){
            return Long.compare(this.weight, node.weight);
        }

        @Override
        public String toString(){
            return "["+this.from+" to "+this.to+"="+this.weight+"]";
        }
    }
    static int[] parents;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 16398;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        parents=new int[N+1];
        for(int i=1;i<=N;i++){
            parents[i]=i;
        }
        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(int x=1;x<=N;x++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int y=1;y<=N;y++){
                if(x==y){
                    st.nextToken();
                    continue;
                }
                long weight=Long.parseLong(st.nextToken());
                pq.offer(new Node(x, y, weight));
                // System.out.println(x+" to "+y+"="+weight);
            }
        }

        long result=0L;
        int count=0;
        while(!pq.isEmpty()){
            Node current=pq.poll();
            // System.out.println(current);
            int from=current.from;
            int to=current.to;
            long weight=current.weight;

            if(union(from, to)){
                // System.out.println(from+"과"+to+"를 연결");
                // System.out.println(Arrays.toString(parents));
                result+=weight;
                count+=1;
                if(count==(N-1)){
                    break;
                }
            }
        }
        System.out.println(result);
        br.close();
    }
    static boolean union(int x, int y){
        int parentX=find(x);
        int parentY=find(y);

        if(parentX>parentY){
            parents[parentX]=parentY;
            return true;
        }else if(parentX==parentY){
            return false;
        }else{
            parents[parentY]=parentX;
            return true;
        }
    }
    static int find(int x){
        if(parents[x]!=x){
            return parents[x]=find(parents[x]);
        }
        return x;
    }
}
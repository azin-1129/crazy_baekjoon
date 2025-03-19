package Code;

import java.util.*;
import java.io.*;

class BOJ_4386{
    static class Node4386{
        int idx;
        double weight;

        public Node4386(int idx, double weight){
            this.idx=idx;
            this.weight=weight;
        }
    }
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=4386;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        List<List<Node4386>> graph;

        int N=Integer.parseInt(br.readLine());
        double[][] positions=new double[N][2];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());

            positions[i][0]=Double.parseDouble(st.nextToken());
            positions[i][1]=Double.parseDouble(st.nextToken());
        }

        graph=new ArrayList<>();
        for(int x=0;x<N;x++){
            graph.add(new ArrayList<>());
            for(int y=0;y<N;y++){
                if(x!=y){
                    graph.get(x).add(new Node4386(y, calcDistance(positions[x], positions[y])));
                }
            }
        }
        
        double result=0;
        boolean[] visited=new boolean[N];
        PriorityQueue<Node4386> pq=new PriorityQueue<>(new Comparator<Node4386>(){
            @Override
            public int compare(Node4386 n1, Node4386 n2){
                return (int)(n1.weight-n2.weight);
            }
        });

        pq.offer(new Node4386(0,0));
        while(!pq.isEmpty()){
            Node4386 currentNode=pq.poll();
            int currentNodeIdx=currentNode.idx;

            if(visited[currentNodeIdx]){
                continue;
            }

            visited[currentNodeIdx]=true;
            // System.out.println((currentNodeIdx+1)+"를 등록했습니다.");
            // System.out.println(currentNode.weight);
            result+=currentNode.weight;

            for(Node4386 nextNode:graph.get(currentNodeIdx)){
                if(visited[nextNode.idx]){
                    continue;
                }
                pq.add(nextNode);
            }
        }
        // System.out.println();
        // System.out.println((start+1)+"부터 출발한 결과:"+resultTemp);
        
        System.out.println(Math.round(result*100)/100.0);
        br.close();
    }
    public static double calcDistance(double[] point1, double[] point2){
        return Math.sqrt(
            Math.pow((point1[0]-point2[0]), 2)
            +
            Math.pow((point1[1]-point2[1]), 2)
        );
    }
}
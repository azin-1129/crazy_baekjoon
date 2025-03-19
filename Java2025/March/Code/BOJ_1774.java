package Code;

import java.util.*;

import org.w3c.dom.Node;

import java.io.*;

class BOJ_1774{
    static class Node1774{
        int idx;
        double weight;

        public Node1774(int idx, double weight){
            this.idx=idx;
            this.weight=weight;
        }
    }
    static int N;
    static List<List<Node1774>> graph;
    static List<List<Integer>> setUp;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1774;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[][] positions=new int[N+1][2];
        int start=0;
        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            positions[i][0]=x;
            positions[i][1]=y;

            if(x==y){
                start=x;
            }
        }

        graph=new ArrayList<>();
        graph.add(new ArrayList<>());
        for(int x=1;x<=N;x++){
            graph.add(new ArrayList<>());
            for(int y=1;y<=N;y++){
                graph.get(x).add(new Node1774(y, calcDistance(positions[x], positions[y])));
            }
        }

        double setUpValue=0;
        setUp=new ArrayList<>();
        for(int i=0;i<=N;i++){
            setUp.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            setUpValue+=graph.get(x).get(y-1).weight;
            System.out.println("셋업 값을 계산합니다:"+setUpValue);
            setUp.get(x).add(y);
        }

        System.out.println(prim(start)-setUpValue);
        // Math.round(result*100)/100.0;
        br.close();
    }
    public static double prim(int start){
        boolean[] visited=new boolean[N+1];
        PriorityQueue<Node1774> pq=new PriorityQueue<>(new Comparator<Node1774>(){
            @Override
            public int compare(Node1774 n1, Node1774 n2){
                return (int) (n1.weight-n2.weight);
            }
        });

        pq.offer(new Node1774(start, 0));

        double result=0;
        while(!pq.isEmpty()){
            Node1774 currentNode=pq.poll();
            int currentNodeIdx=currentNode.idx;
            double currentNodeWeight=currentNode.weight;

            if(visited[currentNodeIdx]){
                continue;
            }

            visited[currentNodeIdx]=true;
            System.out.println(currentNodeIdx+"를 등록했어요.");
            result+=currentNodeWeight;

            int flag=0;
            System.out.println("current Node:"+currentNodeIdx);
            System.out.println("셋업 정보:"+setUp.get(currentNodeIdx));
            for(Node1774 nextNode:graph.get(currentNodeIdx)){
                if(setUp.get(currentNodeIdx).contains(nextNode.idx)){
                    System.out.println(currentNodeIdx+"에서 "+nextNode.idx+"로 가는 경로가 이미 존재해요. 패스합니다.");
                    pq.offer(nextNode);
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                for(Node1774 nextNode:graph.get(currentNodeIdx)){
                    if(!visited[nextNode.idx]){
                        pq.offer(nextNode);
                    }
                }
            }
        }
        return result;
    }
    public static double calcDistance(int[] position1, int[] position2){
        return Math.sqrt(
            Math.pow((position2[0]-position1[0]),2)
            +
            Math.pow((position2[1]-position1[1]),2)
        );
    }
}
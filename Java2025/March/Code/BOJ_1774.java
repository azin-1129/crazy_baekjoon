package Code;

import java.util.*;

import java.io.*;

class BOJ_1774{
    static class Node1774{
        int idx;
        double weight;

        public Node1774(int idx, double weight){
            this.idx=idx;
            this.weight=weight;
        }

        public void setWeight(double weight){
            this.weight=weight;
        }

        @Override
        public String toString(){
            return idx+"번째 node의 weight는 "+weight+" 입니다.";
        }
    }
    static int N;
    static double[][] distances;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1774;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[][] positions=new int[N+1][2];
        distances=new double[N+1][N+1];

        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            positions[i][0]=x;
            positions[i][1]=y;
        }

        for(int x=1;x<=N;x++){
            for(int y=1;y<=N;y++){
                distances[x][y]=calcDistance(positions[x], positions[y]);
            }
        }

        // 이미 연결된 edge
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            // 이미 연결된 곳은 weight 0으로 처리
            distances[x][y]=0;
            distances[y][x]=0;
        }

        // System.out.printf("%.2f", Math.round(prim(1)*100)/100.0);
        System.out.printf("%.2f", prim(1));
        br.close();
    }
    public static double prim(int start){
        boolean[] visited=new boolean[N+1];
        double[] minDist=new double[N+1];
        Arrays.fill(minDist, Double.MAX_VALUE);
        PriorityQueue<Node1774> pq=new PriorityQueue<>(new Comparator<Node1774>(){
            @Override
            public int compare(Node1774 n1, Node1774 n2){
                return Double.compare(n1.weight,n2.weight);
            }
        });
        
        minDist[start]=0;
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
            result+=currentNodeWeight;
            minDist[currentNodeIdx]=0;

            for(int nextNodeIdx=1;nextNodeIdx<=N;nextNodeIdx++){
                if(visited[nextNodeIdx]){
                    continue;
                }

                if((minDist[currentNodeIdx]+distances[currentNodeIdx][nextNodeIdx])<minDist[nextNodeIdx]){
                    minDist[nextNodeIdx]=minDist[currentNodeIdx]+distances[currentNodeIdx][nextNodeIdx];
                    pq.offer(new Node1774(nextNodeIdx, minDist[nextNodeIdx]));
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
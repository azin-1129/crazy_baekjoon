import java.util.*;
import java.io.*;

class BOJ_1240 {
    static class Node{
        int from;
        int to;
        int weight;

        Node(int from, int to, int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public String toString(){
            return from+" to "+to+"="+weight;
        }
    }
    static List<List<Node>> graph=new ArrayList<>();
    static int N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1240;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        int M=Integer.parseInt(st.nextToken());
        for(int i=0;i<N-1;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(from, to, weight));
            graph.get(to).add(new Node(to, from, weight));
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            sb.append(find(from, to)+"\n");
            System.out.println();
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static int find(int from, int to){
        boolean[] visited=new boolean[N+1];
        PriorityQueue<Node> pq=new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Integer.compare(n1.weight, n2.weight);
            }
        });

        for(Node node : graph. get(from)){
            pq.offer(node);
        }
        int cost=0;
        visited[from]=true;
        while(!pq.isEmpty()){
            Node node=pq.poll();
            System.out.println(node);
            int current=node.to;
            int weight=node.weight;

            if(visited[current]){
                continue;
            }
            visited[current]=true;
            cost+=weight;
            if(current==to){
                return cost;
            }
            for(Node next : graph.get(current)){
                pq.offer(next);
            }
        }

        return 0;
    }
}
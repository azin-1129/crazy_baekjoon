import java.util.*;
import java.io.*;

class BOJ_1240 {
    static class Node{
        int idx;
        int weight;

        Node(int idx, int weight){
            this.idx=idx;
            this.weight=weight;
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

            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int from=Integer.parseInt(st.nextToken());
            int to=Integer.parseInt(st.nextToken());
            sb.append(find(from, to)+"\n");
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

        visited[from]=true;
        while(!pq.isEmpty()){
            Node node=pq.poll();
            int current=node.idx;
            int weight=node.weight;

            if(visited[current]){
                continue;
            }
            visited[current]=true;
            if(current==to){
                return weight;
            }
            for(Node next : graph.get(current)){
                pq.offer(new Node(next.idx, next.weight+weight));
            }
        }

        return 0;
    }
}
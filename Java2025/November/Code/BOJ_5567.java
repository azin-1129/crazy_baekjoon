import java.util.*;
import java.io.*;

class BOJ_5567 {
    static class Node{
        int number;
        int depth;

        public Node(int number, int depth){
            this.number=number;
            this.depth=depth;
        }

        @Override
        public String toString(){
            return number+"번 node의 depth:"+depth;
        }
    }
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 5567;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        List<List<Integer>> graph=new ArrayList<>();
        Queue<Node> q=new ArrayDeque<>();
        boolean[] visited;
        int count=0;

        int n=Integer.parseInt(br.readLine());
        visited=new boolean[n+1]; // 노드 번호는 1부터
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        int m=Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        q.offer(new Node(1, 0));
        while(!q.isEmpty()){
            Node current=q.poll();
            int currentNode=current.number;
            int currentDepth=current.depth;
            if(visited[currentNode]){
                continue;
            }
            if(currentDepth>=3){
                break;
            }
            visited[currentNode]=true;
            // System.out.println(currentNode+"번 node를 초대합니다.");
            count+=1;
            for(int nextNode : graph.get(currentNode)){
                q.offer(new Node(nextNode, currentDepth+1));
            }
        }

        System.out.println(count-1); // 1번 node 제외
        br.close();
    }
}
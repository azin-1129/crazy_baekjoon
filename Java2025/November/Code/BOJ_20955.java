import java.util.*;
import java.io.*;

class BOJ_20955 {
    static class Node{
        int before;
        int idx;

        Node(int before, int idx){
            this.before=before;
            this.idx=idx;
        }

        @Override
        public String toString(){
            return idx+"번 node. 이전:"+before;
        }
    }
    static List<List<Integer>> graph=new ArrayList<>();
    static boolean[] visited;
    static int N, cycleClearCount;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum =20955;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        for(int n=0;n<=N;n++){
            graph.add(new ArrayList<>());
        }
        visited=new boolean[N+1];
        int M=Integer.parseInt(st.nextToken());
        for(int m=0;m<M;m++){
            st=new StringTokenizer(br.readLine(), " ");
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // System.out.println(graph);
        boolean isCycleCleared=false;
        do{
            isCycleCleared=deleteCycle();
        }while(!isCycleCleared);

        int count=0;
        for(int i=1;i<=N;i++){
            if(visited[i]){
                continue;
            }
            count+=countGraph(i);
        }
 
        System.out.println(cycleClearCount+(count-1));
        br.close();
    }
    static boolean deleteCycle(){
        boolean[] visited=new boolean[N+1];
        Queue<Node> q=new ArrayDeque<>();
        q.offer(new Node(-1, 1));
        while(!q.isEmpty()){
            Node node=q.poll();
            System.out.println(node);
            int before=node.before;
            int current=node.idx;
            if(visited[current]){
                continue;
            }
            visited[current]=true;
            for(int next : graph.get(current)){
                if(visited[next]){
                    if(before==next){ // 회귀는 패스
                        continue;
                    }else{ // 사이클 발생
                        System.out.println(graph);
                        System.out.println(current+"와 "+next+"를 끊습니다.");
                        int idx1=graph.get(current).indexOf(next);
                        graph.get(current).remove(idx1);
                        int idx2=graph.get(next).indexOf(current);
                        graph.get(next).remove(idx2);
                        cycleClearCount+=1;
                        return false;
                    }
                }else{
                    q.offer(new Node(current, next));
                }
            }
        }

        System.out.println("사이클이 없어요.");
        return true;
    }
    static int countGraph(int start){
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(start);
        while(!q.isEmpty()){
            int current=q.poll();
            if(visited[current]){
                continue;
            }
            visited[current]=true;
            for(int next : graph.get(current)){
                q.offer(next);
            }
        }

        return 1;
    }
}
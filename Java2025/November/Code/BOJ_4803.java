import java.util.*;
import java.io.*;

class BOJ_4803 {
    static boolean[] visited;
    static List<List<Integer>> graph;
    static int n, treeCount;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 4803;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        int testCase=1;
        while(true){
            treeCount=0;
            graph=new ArrayList<>();
            st=new StringTokenizer(br.readLine(), " ");
            n=Integer.parseInt(st.nextToken()); // 정점 개수
            visited=new boolean[n+1];
            int m=Integer.parseInt(st.nextToken()); // 간선 개수

            if(n==0 && m==0){
                break;
            }

            for(int i=0;i<=n;i++){
                graph.add(new ArrayList<>());
            }

            for(int i=0;i<m;i++){
                st=new StringTokenizer(br.readLine(), " ");
                int from=Integer.parseInt(st.nextToken());
                int to=Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            for(int v=1;v<=n;v++){
                if(visited[v]){
                    continue;
                }
                bfs(v);
                System.out.println(Arrays.toString(visited));
            }

            sb.append("Case "+testCase+": ");
            if(treeCount==0){
                sb.append("No trees."+"\n");
            }else if(treeCount==1){
                sb.append("There is one tree"+"\n");
            }else{
                sb.append("A forest of "+treeCount+" trees."+"\n");
            }
            testCase++;
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void bfs(int v){
        int[] parent=new int[n+1];
        Queue<Integer> q=new ArrayDeque<>();
        parent[1]=1;
        q.offer(v);
        while(!q.isEmpty()){
            int current=q.poll();
            if(visited[current]){
                continue;
            }
            System.out.println(Arrays.toString(parent));
            System.out.println("현재 "+current+"방문중.");
            visited[current]=true;
            for(int next : graph.get(current)){
                if(visited[next]){
                    System.out.println("가봤던 곳");
                    return;
                }
                // 사이클일 경우, 아묻따 return. 트리가 아님
                // if(parent[current]==next){
                //     System.out.println("사이클인디요..");
                //     return;
                // }
                parent[next]=parent[current];
                q.offer(next);
            }
        }
        treeCount+=1;
        return;
    }
}
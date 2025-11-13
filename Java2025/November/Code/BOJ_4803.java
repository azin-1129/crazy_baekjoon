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

                System.out.println("시작점: "+v);
                if(!dfs(v)){
                    System.out.println("사이클이라고 봄.");
                }else{
                    System.out.println("괜찮지 않나요?");
                }
                System.out.println(Arrays.toString(visited));
            }

            sb.append("Case "+testCase+": ");
            if(treeCount==0){
                sb.append("No trees."+"\n");
            }else if(treeCount==1){
                sb.append("There is one tree."+"\n");
            }else{
                sb.append("A forest of "+treeCount+" trees."+"\n");
            }
            testCase++;
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static boolean dfs(int v){
        int[] parent=new int[n+1];
        Stack<Integer> q=new Stack<>();
        parent[v]=v;
        q.push(v);
        while(!q.isEmpty()){
            int current=q.pop();
            visited[current]=true;
            System.out.println(current+" 방문중.");
            System.out.println(Arrays.toString(parent));
            for(int next : graph.get(current)){
                if(visited[next]){
                    if(parent[current]==next){
                        continue;
                    }else{
                        return false;
                    }
                }

                parent[next]=current;
                q.push(next);
            }
        }
        treeCount+=1;
        return true;
    }
}
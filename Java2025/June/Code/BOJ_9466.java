package Code;

import java.util.*;
import java.io.*;

class BOJ_9466{
    static ArrayList<Integer> NodesInDfs;
    static int start;
    static boolean[] enabled;
    static int[] graph;
    static int result;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9466;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int t=0;t<T;t++){
            result=0;
            int N=Integer.parseInt(br.readLine());
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            graph=new int[N+1];
            for(int i=1;i<=N;i++){
                graph[i]=Integer.parseInt(st.nextToken());
            }

            enabled=new boolean[N+1];
            for(int i=1;i<=N;i++){
                NodesInDfs=new ArrayList<>();
                NodesInDfs.add(i);
                start=i;
                dfs(graph[i]);
                // System.out.println("DFS가 끝났습니다.");
                // System.out.println();
            }

            sb.append((N-result)+"\n");

            // System.out.println("팀을 결성한 노드 이력입니다.");
            // System.out.println(Arrays.toString(enabled));
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void dfs(int current){
        // System.out.println("현재 노드는 "+current);
        // 1
        if(enabled[current]){
            // System.out.println("해당 노드는 이미 팀이 있습니다.");
            return;
        }
        
        // 2
        if(graph[current]==current){
            // System.out.println("해당 노드는 자기 자신을 참조합니다.");
            enabled[current]=true;
            result+=1;
            return;
        }
        
        // 3
        if(current!=start & NodesInDfs.contains(current)){
            // System.out.println("순환이 어긋났습니다.");
            int idx=NodesInDfs.indexOf(current);
            for(int i=idx;i<NodesInDfs.size();i++){
                enabled[NodesInDfs.get(i)]=true;
                result+=1;
            }
            return;
        }
        
        // 4
        if(start==current){
            // System.out.println("순환 완료되었습니다.");
            // System.out.println(NodesInDfs);
            for(int node:NodesInDfs){
                enabled[node]=true;
                result+=1;
            }
            return;
        }
        
        NodesInDfs.add(current);
        dfs(graph[current]);
    }
}
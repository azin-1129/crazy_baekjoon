import java.util.*;
import java.io.*;

class BOJ_1707 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1707;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;
        // 1~N정점을 하나씩 선택하며,
        // 나머지 정점들이 서로 인접하지 않는지 확인.
        // 만약 인접하는 정점이 있다면, 실패 처리하고 다음 케이스
        // 인접 = 선택한 정점을 거치지 않고 이어지는 길이 있는가?
        int K=Integer.parseInt(br.readLine());
        for(int k=0;k<K;k++){
            List<List<Integer>> graph=new ArrayList<>();
            st=new StringTokenizer(br.readLine(), " ");

            int V=Integer.parseInt(st.nextToken());
            for(int v=0;v<=V;v++){
                graph.add(new ArrayList<>());
            }

            int E=Integer.parseInt(st.nextToken());
            for(int e=0;e<E;e++){
                st=new StringTokenizer(br.readLine(), " ");
                int from=Integer.parseInt(st.nextToken());
                int to=Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            List<Integer> groupA=new ArrayList<>();
            List<Integer> groupB=new ArrayList<>();
            for(int a=1;a<=V;a++){ // 정점 그룹 A
                groupA.add(a);
                for(int b=(a+1);b<=V;b++){ // 정점 그룹 B
                    // b의 원소가 a의 원소와 인접한가?
                }
            }
        }
        br.close();
    }
}
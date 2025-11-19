import java.util.*;
import java.io.*;

class BOJ_15681 {
    static List<List<Integer>> graph=new ArrayList<>();
    static List<List<Integer>> childInfo=new ArrayList<>();
    static int[] childCount, parent;
    static int N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 15681;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        childCount=new int[N+1];
        parent=new int[N+1];
        for(int n=0;n<=N;n++){
            graph.add(new ArrayList<>());
            childInfo.add(new ArrayList<>());
        }
        int R=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());
        for(int n=0;n<N-1;n++){
            st=new StringTokenizer(br.readLine(), " ");
            int U=Integer.parseInt(st.nextToken());
            int V=Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }

        Arrays.fill(childCount, 1);
        calc(R, -1);
        StringBuilder sb=new StringBuilder();
        for(int q=0;q<Q;q++){
            int U=Integer.parseInt(br.readLine());
            sb.append(childCount[U]+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }

    static void calc(int current, int parent){
        for(int next : graph.get(current)){
            if(next!=parent){
                calc(next, current);
            }
        }
        if(parent!=-1){
            childCount[parent]+=childCount[current];
        }
    }
}
import java.util.*;
import java.io.*;

class BOJ_15681 {
    static int[] parent;
    static int[] count;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 15681;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        parent=new int[N+1];
        count=new int[N+1];
        for(int n=1;n<=N;n++){
            count[n]=1;
        }
        int R=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());
        for(int n=0;n<N-1;n++){
            st=new StringTokenizer(br.readLine(), " ");
            int U=Integer.parseInt(st.nextToken());
            int V=Integer.parseInt(st.nextToken());
            parent[V]=U;
            calc(U, V);
        }

        for(int q=0;q<Q;q++){
            int U=Integer.parseInt(br.readLine());
        }
        System.out.println(Arrays.toString(parent));
        System.out.println(Arrays.toString(count));
        br.close();
    }
    static void calc(int current, int child){
        if(current==0){ // root
            return;
        }
        count[current]+=count[child];
        calc(find(current), current);
    }
    static int find(int x){
        return parent[x];
    }
}
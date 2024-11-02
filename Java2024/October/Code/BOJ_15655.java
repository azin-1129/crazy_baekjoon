package Code;
import java.util.*;
import java.io.*;

public class BOJ_15655 {
    static int N,M;
    static int[] numbers, result;
    static boolean[] visited;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input15655.txt"));
    
        String[] inputs=br.readLine().split(" ");

        N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);

        visited=new boolean[N+1];
        result=new int[M];
        numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(numbers);

        combination(0, 0);

        System.out.print(sb.toString());

        br.close();
    }
    static void combination(int start, int depth){
        if(depth==M){
            for(int i=0;i<N;i++){
                if(visited[i]){
                    sb.append(numbers[i]);

                    if(i!=(N-1)){
                        sb.append(" ");
                    }
                }
            }
            sb.append("\n");
            return;
        }

        for(int i=start;i<N;i++){
            if(!visited[i]){
                visited[i]=true;
                combination(i+1, depth+1);
                visited[i]=false;
            }
        }
    }
}

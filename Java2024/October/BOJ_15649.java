import java.io.BufferedReader;
import java.io.FileReader;

public class BOJ_15649 {
    static int N,M;
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input15649.txt"));
    
        String[] inputs=br.readLine().split(" ");

        N=Integer.parseInt(inputs[0]);
        M=Integer.parseInt(inputs[1]);

        int[] numbers=new int[N];

        for(int i=0;i<N;i++){
            numbers[i]=i+1;
        }

        permutation(numbers, new int[M], new boolean[N], 0);
    }
    static void permutation(int[] numbers, int[] result, boolean[] visited, int depth){
        if(depth==M){
            for(int i=0;i<M;i++){
                System.out.print(result[i]);
                if(i!=(M-1)){
                 System.out.print(" ");   
                }
            }

            System.out.println();

            return;
        }

        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i]=true;

                result[depth]=numbers[i];

                permutation(numbers, result, visited, depth+1);

                visited[i]=false;
            }
        }
    }
}

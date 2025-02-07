package Code;

import java.util.Arrays;

public class permAndComb {
    static int[] numbers=new int[]{1,2};
    static int[] result=new int[2];
    static boolean[] visited=new boolean[2];
    static int N=2;
    public static void main(String[] args) throws Exception {
        // perm(2,0);
        // dupPerm(2, 0);
        // comb(0, 2, 0);
        // dupComb(0, 2, 0);
    }   
    static void perm(int r, int depth){
        if(depth==r){
            System.out.println(Arrays.toString(result));;
            return;
        }

        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i]=true;
                result[depth]=numbers[i];
                perm(r, depth+1);
                visited[i]=false;
            }
        }
    }

    static void dupPerm(int r, int depth){ // perm - visited
        if(r==depth){
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i=0;i<N;i++){
            result[depth]=numbers[i];
            dupPerm(r, depth+1);
        }
    }

    static void comb(int start, int r, int depth){ // start 보유
        if(r==depth){
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i=start;i<N;i++){
            result[depth]=numbers[i];
            comb(i+1, r, depth+1);
        }
    }

    static void dupComb(int start, int r, int depth){ // 재귀 시 i+1
        if(r==depth){
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i=start;i<N;i++){
            result[depth]=numbers[i];
            dupComb(i, r, depth+1);
        }
    }
}

import java.util.*;
import java.io.*;

class BOJ_11403 {
    static int[][] matrix;
    static int N;
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 11403;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        N=Integer.parseInt(br.readLine());
        matrix=new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                matrix[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(matrix[i][j]==1){
                    find(i, j);
                }
            }
        }

        for(int x=0;x<N;x++){
            System.out.println(Arrays.toString(matrix[x]));
        }
        br.close();
    }
    static void find(int startRow, int currentRow){
        if(startRow==currentRow){
            return;
        }

        for(int i=0;i<N;i++){
            if(matrix[currentRow][i]==1){
                matrix[startRow][i]=1;

                    find(startRow,i);
                
            }
        }
    }
}
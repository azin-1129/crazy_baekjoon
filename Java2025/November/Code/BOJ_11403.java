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
                int value=Integer.parseInt(st.nextToken());
                if(value==0){
                    value=101; // Math.min 비교가 가능하되, int 범위 선에서 깨지지 않는 값
                }
                matrix[i][j]=value;
            }
        }

        for(int k=0;k<N;k++){
            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    matrix[x][y]=Math.min(matrix[x][y], matrix[x][k]+matrix[k][y]);
                }
            }
        }

        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(matrix[x][y]==101){
                    matrix[x][y]=0;
                }else if(matrix[x][y]>1){
                    matrix[x][y]=1;
                }
            }
        }

        for(int x=0;x<N;x++){
            System.out.println(Arrays.toString(matrix[x]).replaceAll("[\\[\\],]",""));
        }
        br.close();
    }
}
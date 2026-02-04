package Code;

import java.util.*;
import java.io.*;

class BOJ_1969 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 1969;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));
        StringTokenizer st;
        st= new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        String sample="ACGT";
        int[][] count=new int[4][M];
        char[][] dnas=new char[N][M];
        for(int i=0;i<N;i++){
            String dna=br.readLine();
            for(int j=0;j<M;j++){
                dnas[i][j]=dna.charAt(j);
            }
        }

        // M까지의 idx 별로 제일 적게 등장한 알파벳을 (A, C, G, T) 중 선정
        for(int y=0;y<M;y++){
            for(int x=0;x<N;x++){
                switch(dnas[x][y]){
                    case 'A':
                        count[0][y]++;
                        break;
                    case 'C':
                        count[1][y]++;
                        break;
                    case 'G':
                        count[2][y]++;
                        break;
                    case 'T':
                        count[3][y]++;
                        break;
                }
            }
        }

        String result="";
        int hammingDistance=0;
        for(int x=0;x<M;x++){
            int maximumValue=0;
            int maximumIdx=0;
            for(int y=0;y<4;y++){
                if(maximumValue<count[y][x]){
                    maximumValue=count[y][x];
                    maximumIdx=y;
                }
            }
            hammingDistance+=(N-maximumValue);
            result+=sample.charAt(maximumIdx);
        }

        System.out.println(result);
        System.out.println(hammingDistance);
        br.close();
    }
}
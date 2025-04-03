package Code;

import java.util.*;
import java.io.*;

class BOJ_3003{
    static int CHESS_SIZE=6;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=3003;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());

        int[] chessSet={1, 1, 2, 2, 2, 8};
        int[] required=new int[CHESS_SIZE];
        for(int i=0;i<CHESS_SIZE;i++){
            int whitePiece=Integer.parseInt(st.nextToken());

            required[i]=chessSet[i]-whitePiece;
        }

        System.out.println(Arrays.toString(required).replaceAll("[\\[\\],]",""));
        br.close();
    }
}
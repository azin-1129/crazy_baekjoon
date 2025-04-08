package Code;

import java.util.*;
import java.io.*;

class BOJ_2566{
    static int ARR_SIZE=9;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2566;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        int maxXIdx=0;
        int maxYIdx=0;
        int result=-1;
        for(int x=0;x<ARR_SIZE;x++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int y=0;y<ARR_SIZE;y++){
                int arrValue=Integer.parseInt(st.nextToken());

                if(arrValue>result){
                    maxXIdx=x;
                    maxYIdx=y;
                    result=arrValue;
                }
            }
        }

        System.out.println(result);
        System.out.println((maxXIdx+1)+" "+(maxYIdx+1));
        br.close();
    }
}
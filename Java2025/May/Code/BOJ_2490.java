package Code;

import java.util.*;
import java.io.*;

class BOJ_2490{
    static int LINE_COUNT=3;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0;i<LINE_COUNT;i++){
            st=new StringTokenizer(br.readLine(), " ");

            int count=0;
            while(st.hasMoreTokens()){
                count+=Integer.parseInt(st.nextToken());
            }

            switch(count){
                case 0: // 윷
                    System.out.println("D");
                    break;
                case 1: // 걸
                    System.out.println("C");
                    break;
                case 2: // 개
                    System.out.println("B");
                    break;
                case 3: // 도
                    System.out.println("A");
                    break;
                case 4: // 윷
                    System.out.println("E");
                    break;
            }
        }
        // 0이 1개면 도, 0이 2개면 개, 0이 3개면 걸, 0이 4개면 윷, 0이 0개면 모

        br.close();
    }
}
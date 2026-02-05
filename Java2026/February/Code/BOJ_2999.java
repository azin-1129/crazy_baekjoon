package Code;

import java.util.*;
import java.io.*;

class BOJ_2999 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum = 2999;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        // n*n=N일 때까지 약수를 구한다.
        // 제곱근 이하의 수들 중, 가장 큰 R&C 쌍을 고른다.
        // char[][] arr=new char[R][C];
        // 메시지 to 행렬

        // N의 약수들을 구한다.
        String str=br.readLine();
        int N=(str.length());
        int R=0;
        int C=0;
        for(int i=1;i<=Math.sqrt(N);i++){
            if(N%i==0){
                int RTemp=i;
                int CTemp=N/i;

                if(R<RTemp){
                    if(RTemp>CTemp){
                        int temp=RTemp;
                        RTemp=CTemp;
                        CTemp=temp;
                    }
                    R=RTemp;
                    C=CTemp;
                }
            }
        }

        char[][] arr=new char[R][C];
        // RxC 단위로 스플릿
        for(int x=0;x<R;x++){
            for(int y=0;y<C;y++){
                // System.out.println((x*C+y));
                int idx=(x*C)+y;
                arr[x][y]=str.charAt(idx);
            }
        }

        // 디버깅
        for(int x=0;x<R;x++){
            System.out.println(Arrays.toString(arr[x]));
        }
        System.out.println("R:"+R+", C:"+C);
        
        // 전치
        StringBuilder sb=new StringBuilder();
        char[][] result=new char[C][R];
        for(int y=0;y<C;y++){
            for(int x=0;x<R;x++){
                result[y][x]=arr[x][y];
            }
        }
        // 디버깅
        for(int x=0;x<C;x++){
            System.out.println(Arrays.toString(result[x]));
        }

        for(int x=0;x<C;x++){
            for(int y=0;y<R;y++){
                sb.append(result[x][y]);
            }
        }
        System.out.println(sb);
        br.close();
    }
}
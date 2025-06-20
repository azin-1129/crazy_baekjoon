package Code;

import java.util.*;
import java.io.*;

class BOJ_2447{
    static String[][] result;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2447;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        result=new String[N][N];

        split(0, N, 0, N);
        
        StringBuilder sb=new StringBuilder();
        for(int x=0;x<N;x++){
            for(int y=0;y<N;y++){
                if(result[x][y]==null){
                    sb.append(" ");
                }else{
                    sb.append(result[x][y]);
                }
            }
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void split(int startX, int endX, int startY, int endY){
        // System.out.println("split");
        // x는 (endX-startX)/3씩 증가한다.
        int diff=(endX-startX)/3;
        
        if(diff==1){ // range=3
            // System.out.println("range가 3입니다.");
            marking(startX, endX, startY, endY);
            return;
        }
        // 9분할 하는 코드
        for(int x=startX;x<endX;x+=diff){
            for(int y=startY;y<endY;y+=diff){
                if((x==(startX+diff)) & (y==(startY+diff))){ // 가운데 포지션
                    // System.out.println("가운데라서 건너 뛰었어요.");
                    continue;
                }
                // System.out.println("다음 재귀입니다:");
                // System.out.println(x+","+(x+diff)+" to "+y+","+(y+diff));
                split(x, x+diff, y, y+diff);
            }
        }
    }

    static void marking(int startX, int endX, int startY, int endY){
        for(int x=startX;x<endX;x++){
            for(int y=startY;y<endY;y++){
                if(x==(startX+1) & y==(startY+1)){
                    continue;
                }
                result[x][y]="*";
            }
        }
    }
}
package Code;

import java.util.*;
import java.io.*;

class BOJ_1780{
    static int[] count=new int[3];
    static int[][] map;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1780;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        int N=Integer.parseInt(br.readLine());
        map=new int[N][N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N, N);
        for(int c:count){
            System.out.println(c);
        }

        br.close();
    }
    static void cut(int startX, int startY, int endX, int endY){
        // 해당 종이의 value가 같다면 재귀를 하지 않는다.
        if(isSameValue(startX, startY, endX, endY)){
            count[map[startX][startY]+1]+=1;
            return;
        }else{
            // 잘라봤자 1종이
            if(((endX-startX)==3) & ((endY-startY)==3)){
                plus(startX, startY, endX, endY);
                return;
            }

            for(int x=startX;x<endX;x+=3){
                for(int y=startY;y<endY;y+=3){
                    cut(x, y, x+3, y+3);
                }
            }
	    }
    }
    static boolean isSameValue(int startX, int startY, int endX, int endY){
        int defaultValue=map[startX][startY];
        for(int x=startX;x<endX;x++){
            for(int y=startY;y<endY;y++){
                if(map[x][y]!=defaultValue){
                    return false;
                }
            }
        }

        return true;
    }
    static void plus(int startX, int startY, int endX, int endY){
        for(int x=startX;x<endX;x++){
            for(int y=startY;y<endY;y++){
                count[map[x][y]+1]+=1;
            }
        }
    }
}
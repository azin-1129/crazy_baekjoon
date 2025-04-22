package Code;

import java.util.*;
import java.io.*;

class BOJ_3009{
    static int LINE_COUNT=3;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=3009;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] xCount=new int[1001];
        int[] yCount=new int[1001];

        HashSet<Integer> xPoints=new HashSet<>();
        HashSet<Integer> yPoints=new HashSet<>();
        for(int i=0;i<LINE_COUNT;i++){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            xCount[x]+=1;
            yCount[y]+=1;

            xPoints.add(x);
            yPoints.add(y);
        }

        int xPoint=0;
        int yPoint=0;
        for(int x:xPoints){
            if(xCount[x]==1){
                xPoint=x;
            }
        }
        for(int y:yPoints){
            if(yCount[y]==1){
                yPoint=y;
            }
        }
        // x, y가 한 번씩밖에 나오지 않은 값

        System.out.println(xPoint+" "+yPoint);
        br.close();
    }
}
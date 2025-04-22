package Code;

import java.util.*;
import java.io.*;

class BOJ_1085{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1085;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");

        // 현 위치 (x, y)
        int x=Integer.parseInt(st.nextToken());
        int y=Integer.parseInt(st.nextToken());

        // 직사각형 오른쪽 위 꼭짓점 (w, h)
        int w=Integer.parseInt(st.nextToken());
        int h=Integer.parseInt(st.nextToken());

        // x가 0과 w중 어디에 가까운가?
        // y가 0과 h중 어디에 가까운가?
        // x와 w-x 비교
        // y와 h-y 비교

        // example
        // x=6, y=2, w=10, h=3
        // 6과 4 비교=4
        // 2와 3-2 비교=1

        System.out.println(calc(new int[]{x, w-x, y, h-y}));
        br.close();
    }
    static int calc(int[] args){
        int min=1000;
        
        for(int i=0;i<args.length;i++){
            min=Math.min(min, args[i]);
        }

        return min;
    }
}
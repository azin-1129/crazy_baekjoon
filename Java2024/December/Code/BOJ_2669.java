package Code;

import java.util.*;
import java.io.*;

class BOJ_2669{
    static int MAX_LENGTH=100;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2669;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        boolean[][] visited=new boolean[MAX_LENGTH][MAX_LENGTH];

        int count=0;

        for(int i=0;i<4;i++){
            st=new StringTokenizer(br.readLine()," ");

            int leftDownX=Integer.parseInt(st.nextToken());
            int leftDownY=Integer.parseInt(st.nextToken());
            int rightUpX=Integer.parseInt(st.nextToken());
            int rightUpY=Integer.parseInt(st.nextToken());

            // System.out.println(leftDownX+" to "+rightUpX+", "+leftDownY+" to "+rightUpY);
            // (left_down_x, right_up_y) 부터 (right_up_x, left_down_y) 까지 for문

            for(int x=leftDownX;x<rightUpX;x++){ // 1 to 4
                // System.out.println("x");
                for(int y=leftDownY;y<rightUpY;y++){ // 2 to 4
                    // System.out.println("y");
                    if(!visited[x][y]){
                        visited[x][y]=true;
                        count+=1;
                    }
                }
            }
        }

        System.out.println(count);

        br.close();
    }
}
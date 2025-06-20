package Code;

import java.util.*;
import java.io.*;

class BOJ_2630{
    static int[] count=new int[2];
    static int[][] paper;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2630;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        paper=new int[N][N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                paper[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        cut(0, N, 0, N);
        StringBuilder sb=new StringBuilder();
        for(int c:count){
           sb.append(c+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
    static void cut(int startX, int endX, int startY, int endY){
        // System.out.println(startX+","+startY+" to "+endX+","+endY);
        if((endX-startX)==1){
            count[paper[startX][startY]]+=1;
            return;
        }
        if(isSameValue(startX, endX, startY, endY)){
            count[paper[startX][startY]]+=1;
            return;
        }
        
        int diff=(endX-startX)/2;
        for(int x=startX;x<endX;x+=diff){
            for(int y=startY;y<endY;y+=diff){
                // System.out.println(x+","+y+" to "+(x+diff)+","+(y+diff)); // start
                // end?
                cut(x, x+diff, y, y+diff);
            }
        }
    }
    static boolean isSameValue(int startX, int endX, int startY, int endY){
        int defaultValue=paper[startX][startY];
        for(int x=startX;x<endX;x++){
            for(int y=startY;y<endY;y++){
                if(paper[x][y]!=defaultValue){
                    return false;
                }
            }
        }
        
        return true;
    }
}

import java.util.*;
import java.io.*;

public class BOJ_1987 {
    static int[][] map;
    static int R,C,maxDepth;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\";
        String fileName="input1987.txt";
        BufferedReader br=new BufferedReader(new FileReader(path+fileName));

        String[] inputs=br.readLine().split(" ");
        R=Integer.parseInt(inputs[0]);
        C=Integer.parseInt(inputs[1]);

        map=new int[R][C];

        for(int x=0;x<R;x++){
            String string=br.readLine();
            for(int y=0;y<C;y++){
                map[x][y]=(int)string.charAt(y);
            }
        }

        visited=new boolean[(int)'z'+1];

        DFS(0,0, 1);

        System.out.println(maxDepth);
        br.close();
    }
    static void DFS(int x, int y, int depth){
        // System.out.println(x+","+y+" 방문중");
        // System.out.println("alphabets 상태");
        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};

        visited[map[x][y]]=true;
        if(depth>maxDepth){
            maxDepth=depth;
        }

        for(int d=0;d<4;d++){
            int nextX=x+dx[d];
            int nextY=y+dy[d];

            if(nextX<0 || nextX>=R || nextY<0 || nextY>=C){
                continue;
            }

            if(visited[map[nextX][nextY]]){
                continue;
            }

            DFS(nextX, nextY, depth+1);

        }
        
        // return alphabets.size();
        visited[map[x][y]] = false;
    }
}

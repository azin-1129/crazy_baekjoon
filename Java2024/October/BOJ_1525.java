import java.util.*;
import java.io.*;

class BOJ_1525{
    static class NodeInfo{
        int x;
        int y;
        int depth;
        boolean[][] visited;
        
        NodeInfo(int x, int y, int depth, boolean[][] visited){
            this.x=x;
            this.y=y;
            this.depth=depth;
            this.visited=visited;
        }
    }

    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input1525.txt"));

        br.close();
    }
    static void BFS(int x, int y){
        Queue<NodeInfo> q=new ArrayDeque<>();

        q.add(new NodeInfo(x, y, 0, new boolean[3][3]));

        while(!q.isEmpty()){
            NodeInfo current=q.poll();

            int cx=current.x;
            int cy=current.y;
            int cd=current.depth;
            boolean[][] cVisited=current.visited;

            for(int i=0;i<4;i++){
                int nx=cx+dx[i];
                int ny=cy+dy[i];

                if(nx<0 || ny<0 || nx>=3 || ny>=3){
                    continue;
                }

                if(cVisited[nx][ny]){
                    continue;
                }

                cVisited[nx][ny]=true;
                q.add(new NodeInfo())
            }
        }
    }
}
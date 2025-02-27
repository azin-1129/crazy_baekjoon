import java.util.*;
import java.io.*;

class Node1018{
    int x;
    int y;
    int color;

    Node1018(int x, int y, int color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
}
class BOJ_1018{
    static int result=Integer.MAX_VALUE;
    static int N,M;
    static int[][] whiteGrid, blackGrid;
    static int[] dx={1, 0};
    static int[] dy={0, 1};
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1018;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N=inputs[0];
        M=inputs[1];

        int[][] grid=new int[N][M];
        whiteGrid=new int[N][M];
        blackGrid=new int[N][M];

        for(int i=0;i<N;i++){
            String[] colors=br.readLine().split("");
            
            int idx=0;
            for(int j=0;j<colors.length;j++){
                String color=colors[j];
                if(color.equals("W")){
                    grid[i][idx]=0;
                }else{
                    grid[i][idx]=1;
                }
                idx++;
            }
        }

        for(int x=0;x<N;x++){
            System.arraycopy(grid[x], 0, whiteGrid[x], 0, grid[x].length);
        }

        for(int x=0;x<N;x++){
            System.arraycopy(grid[x], 0, blackGrid[x], 0, grid[x].length);
        }

        for(int x=0;x<=(N-8);x++){
            for(int y=0;y<=(M-8);y++){
                bfs(0, whiteGrid, x, y);
                for(int z=0;z<N;z++){
                    System.arraycopy(grid[z], 0, whiteGrid[z], 0, grid[z].length);
                }
            }
        }

        for(int x=0;x<=(N-8);x++){
            for(int y=0;y<=(M-8);y++){
                bfs(1, blackGrid, x, y);
                for(int z=0;z<N;z++){
                    System.arraycopy(grid[z], 0, blackGrid[z], 0, grid[z].length);
                }
            }
        }

        System.out.println(result);
        br.close();
    }

    static void bfs(int startColor, int[][] grid, int x, int y){
        Queue<Node1018> q=new ArrayDeque<>();
        boolean[][] visited=new boolean[N][M];

        q.offer(new Node1018(x, y, startColor));

        int coloring=0;
        if(grid[x][y]!=startColor){
            coloring+=1;
            grid[x][y]=startColor;
        }
        while(!q.isEmpty()){
            Node1018 current=q.poll();

            int cx=current.x;
            int cy=current.y;
            int currentColor=current.color;

            visited[cx][cy]=true;

            for(int i=0;i<2;i++){
                int nx=cx+dx[i];
                int ny=cy+dy[i];

                if(0>nx || 0>ny || nx>=(x+8) || ny>=(y+8)){
                    continue;
                }

                if(visited[nx][ny]){
                    continue;
                }

                if(grid[nx][ny]==currentColor){
                    coloring+=1;
                    
                    if(currentColor==1){
                        grid[nx][ny]=0;
                    }else{
                        grid[nx][ny]=1;
                    }
                }

                q.offer(new Node1018(nx, ny, grid[nx][ny]));
            }
        }

        result=Math.min(coloring, result);
    }
}
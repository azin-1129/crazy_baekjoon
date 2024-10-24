import java.util.*;
import java.io.*;

public class BOJ_1987 {
    static ArrayList<String> charList;
    static String[][] map;
    static boolean[][] visited;
    static int R,C;
    public static void main(String[] args) throws Exception {
        String path=System.getProperty("user.dir")+"\\";
        String fileName="input1987.txt";
        BufferedReader br=new BufferedReader(new FileReader(path+fileName));

        charList=new ArrayList<>();

        String[] inputs=br.readLine().split(" ");
        R=Integer.parseInt(inputs[0]);
        C=Integer.parseInt(inputs[1]);

        map=new String[R][C];
        visited=new boolean[R][C];

        for(int x=0;x<R;x++){
            inputs=br.readLine().split("");
            for(int y=0;y<C;y++){
                map[x][y]=inputs[y];
            }
        }

        charList.add(map[0][0]);
        System.out.println(BFS(0,0));

        br.close();
    }
    static int BFS(int x, int y){
        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1};

        ArrayDeque<int[]> q=new ArrayDeque<>();

        q.add(new int[]{x,y});

        while(!q.isEmpty()){
            int[] current=q.poll();

            int currentX=current[0];
            int currentY=current[1];

            for(int d=0;d<4;d++){
                int nextX=currentX+dx[d];
                int nextY=currentY+dy[d];

                if(nextX<0 || nextX>=R || nextY<0 || nextY>=C){
                    continue;
                }

                if(visited[nextX][nextY]){
                    continue;
                }

                visited[nextX][nextY]=true;

                if(!charList.contains(map[nextX][nextY])){
                    charList.add(map[nextX][nextY]);
                    System.out.println(nextX+","+nextY+" "+map[nextX][nextY]+" 추가");
                    q.add(new int[]{nextX, nextY});
                }
            }
        }
        return charList.size();
    }
}

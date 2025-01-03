package Code;

import java.util.*;
import java.io.*;

class Fish{
    int number;
    int x;
    int y;
    int dir;
    int isAlive;

    Fish(int number, int x, int y, int dir, int isAlive){
        this.number=number;
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.isAlive=isAlive;
    }
}
public class BOJ_19236 {
    public static int[][] map;
    public static Fish[] fish;
    public static int[] dx={-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy={0, -1, -1, -1, 0, 1, 1, 1};
    public static int ans=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map=new int[4][4];
        fish=new Fish[17]; // 1<=Fish number<=16

        for(int i=0;i<4;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<4;j++){
                int number=Integer.parseInt(st.nextToken());

                int dir=Integer.parseInt(st.nextToken())-1;

                fish[number]=new Fish(number, i, j, dir, 1);

                map[i][j]=number;
            }
        }

        int sharkX=0, sharkY=0;
        int sharkD=fish[map[0][0]].dir;
        int eat=map[0][0];
        fish[map[0][0]].isAlive=0;
        map[0][0]=-1;

        dfs(sharkX, sharkY, sharkD, eat);

        System.out.println(ans);
    }
    
    public static void dfs(int sharkX, int sharkY, int sharkD, int eat){
        ans=Math.max(ans, eat);

        int[][] mapTemp=new int[map.length][map.length];
        for(int i=0;i<map.length;i++){
            System.arraycopy(map[i], 0, mapTemp[i], 0, map.length);
        }

        Fish[] fishTemp=new Fish[fish.length];
        for(int i=1;i<=16;i++){
            fishTemp[i]=new Fish(fish[i].number, fish[i].x, fish[i].y, fish[i].dir, fish[i].isAlive);
        }

        moveFish();

        for(int i=1;i<4;i++){
            int nx=sharkX+dx[sharkD]*i;
            int ny=sharkY+dy[sharkD]*i;

            if(nx>=0 && nx<4 && ny>=0 && ny<4 && map[nx][ny]!=0){
                int eatFish=map[nx][ny];
                int nd=fish[eatFish].dir;

                map[sharkX][sharkY]=0;
                map[nx][ny]=-1;
                fish[eatFish].isAlive=0;

                dfs(nx, ny, nd, eat+eatFish);

                fish[eatFish].isAlive=1;
                map[sharkX][sharkY]=-1;
                map[nx][ny]=eatFish;
            }
        }

        for(int j=0;j<map.length;j++){
            System.arraycopy(mapTemp[j], 0, map[j], 0, map.length);
        }

        for(int i=1; i<=16; i++){
            fish[i]=new Fish(fishTemp[i].number, fishTemp[i].x, fishTemp[i].y, fishTemp[i].dir, fishTemp[i].isAlive);
        }
    }

    public static void moveFish(){
        for(int i=1;i<=16;i++){
            if(fish[i].isAlive==0){
                continue;
            }

            int cnt=0;
            int dir=fish[i].dir;
            int nx=0, ny=0;

            while(cnt<8){
                dir%=8;
                fish[i].dir=dir;

                nx=fish[i].x+dx[dir];
                ny=fish[i].y+dy[dir];

                if(nx>=0 && nx<4 && ny>=0 && ny<4 && map[nx][ny]!=-1){
                    if(map[nx][ny]==0){
                        map[fish[i].x][fish[i].y]=0;
                        fish[i].x=nx;
                        fish[i].y=ny;
                        map[nx][ny]=i;
                    }else{
                        int changeFishNumber=fish[map[nx][ny]].number;

                        fish[changeFishNumber].x=fish[i].x;
                        fish[changeFishNumber].y=fish[i].y;
                        map[fish[changeFishNumber].x][fish[changeFishNumber].y]=changeFishNumber;

                        fish[i].x=nx;
                        fish[i].y=ny;
                        map[nx][ny]=i;
                    }
                    break;
                }else{
                    dir+=1;
                    cnt+=1;
                }
            }
        }
    }
}

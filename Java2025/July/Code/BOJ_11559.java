package Code;

import java.util.*;
import java.io.*;

class BOJ_11559{
    static class Puyo{
        int x;
        int y;
        char color;

        public Puyo(int x, int y, char color){
            this.x=x;
            this.y=y;
            this.color=color;
        }

        @Override
        public String toString(){
            return "("+this.x+","+this.y+")"+": "+this.color;
        }
    }
    static int[] dx={-1, 1, 0, 0};
    static int[] dy={0, 0, -1, 1};
    static boolean[][] visited;
    static char[][] field;
    static int BOARD_X_SIZE=12;
    static int BOARD_Y_SIZE=6;
    static Stack<Puyo> destroyStack=new Stack<>();
    static int result=0;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11559;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        visited=new boolean[BOARD_X_SIZE][BOARD_Y_SIZE];
        field=new char[BOARD_X_SIZE][BOARD_Y_SIZE];
        for(int i=0;i<BOARD_X_SIZE;i++){
            String input=br.readLine();
            for(int j=0;j<BOARD_Y_SIZE;j++){
                field[i][j]=input.charAt(j);
            }
        }

        while(true){
            visited=new boolean[BOARD_X_SIZE][BOARD_Y_SIZE];
            for(int x=0;x<BOARD_X_SIZE;x++){
                for(int y=0;y<BOARD_Y_SIZE;y++){
                    if(field[x][y]!='.' & visited[x][y]==false){
                        marking(x, y, field[x][y]);
                    }
                }
            }

            // 테스트중
            // System.out.println("현 field 상태입니다.");
            // for(int x=0;x<BOARD_X_SIZE;x++){
            //     System.out.println(Arrays.toString(field[x]));
            // }

            // System.out.println("다음 위치에 marking 했습니다.");
            // System.out.println(destroyStack);

            if(destroyStack.isEmpty()){
                break;
            }
            destroyPuyo();
            // System.out.println("destroy 작업을 마무리했습니다.");
            // for(int x=0;x<BOARD_X_SIZE;x++){
            //     System.out.println(Arrays.toString(field[x]));
            // }
            result+=1;
            gravity();
            // System.out.println("gravity 작업을 마무리했습니다.");
            // for(int x=0;x<BOARD_X_SIZE;x++){
            //     System.out.println(Arrays.toString(field[x]));
            // }
        }

        System.out.println(result);
        br.close();
    }
    static int marking(int x, int y, char color){
        int count=0;
        // boolean[][] visited=new boolean[BOARD_SIZE][BOARD_SIZE];
        Queue<Puyo> q=new ArrayDeque<>();
        q.offer(new Puyo(x, y, color));
        while(!q.isEmpty()){
            Puyo puyo=q.poll();

            if(visited[puyo.x][puyo.y]){
                continue;
            }

            if(field[puyo.x][puyo.y]==color){
                visited[puyo.x][puyo.y]=true;
                count+=1;
                destroyStack.push(puyo);

                for(int i=0;i<4;i++){
                    int nx=puyo.x+dx[i];
                    int ny=puyo.y+dy[i];
                    if(nx<0 || ny<0 || nx>=BOARD_X_SIZE || ny>=BOARD_Y_SIZE){
                        continue;
                    }
                    q.offer(new Puyo(nx, ny, field[nx][ny]));
                }
            }
        }

        if(count<4){
            for(int i=0;i<count;i++){
                destroyStack.pop();
            }
        }

        return count;
    }
    static void destroyPuyo(){
        while(!destroyStack.isEmpty()){
            Puyo puyo=destroyStack.pop();

            field[puyo.x][puyo.y]='.';
        }
    }
    static void gravity(){
        //  열마다 뿌요 감지해 놓는다.
        // 해당 열을 모두 '.'로 만든 후, [x][BOARD_SIZE-1] 부터 뿌요를 부여한다.
        Queue<Puyo> puyos=new ArrayDeque<>();
        for(int y=0;y<BOARD_Y_SIZE;y++){
            for(int x=BOARD_X_SIZE-1;x>=0;x--){
                if(field[x][y]!='.'){
                    puyos.offer(new Puyo(x, y, Character.valueOf(field[x][y])));
                    field[x][y]='.';
                }
            }

            // System.out.println("끌어내릴 Puyo 좌표");
            // System.out.println(puyos);
            if(puyos.isEmpty()){
                continue;
            }

            int x=BOARD_X_SIZE-1;
            while(!puyos.isEmpty()){
                Puyo puyo=puyos.poll();
                field[x--][y]=puyo.color;
            }
        }
    }
}
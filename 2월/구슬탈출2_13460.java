import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출2_13460 {
    static class Beads{
        int Rx;
        int Ry;
        int Bx;
        int By;
        int dir;
        int cnt;

        @Override
        public String toString() {
            return "Beads [Rx=" + Rx + ", Ry=" + Ry + ", Bx=" + Bx + ", By=" + By + ", dir=" + dir + ", cnt=" + cnt
                    + "]";
        }

        public Beads(int Rx, int Ry, int Bx, int By, int dir, int cnt){
            this.Rx=Rx;
            this.Ry=Ry;
            this.Bx=Bx;
            this.By=By;
            this.dir=dir;
            this.cnt=cnt;
        }

        public Beads(){

        }

        
    }
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/2월/";
        BufferedReader br=new BufferedReader(new FileReader(path+"input13460.txt"));   
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N;
        int M;
        int[][] board;
        int calcCount=Integer.MAX_VALUE;
        int[] dx={-1,1,0,0};    
        int[] dy={0,0,-1,1};
        boolean[][][][][] visited;

        Beads firstBead=new Beads();
        Queue<Beads> q=new LinkedList<>();

        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        board=new int[N][M];
        visited=new boolean[N][M][N][M][4];

        firstBead.cnt=0;

        for(int n=0;n<N;n++){
            // st=new StringTokenizer(br.readLine(),"");
            String[] token=br.readLine().split("");
            for(int m=0;m<M;m++){
                switch(token[m]){
                    case "#":
                        board[n][m]=8;
                        break;
                    case "R":
                        board[n][m]=4;
                        firstBead.Rx=n;
                        firstBead.Ry=m;
                        break;
                    case "B":
                        board[n][m]=3;
                        firstBead.Bx=n;
                        firstBead.By=m;
                        break;
                    case ".":
                        board[n][m]=1;
                        break;
                    case "O":
                        board[n][m]=0;
                        break;
                }
            }
        }

        System.out.println("초기 연산 포맷:"+firstBead);

        for(int i=0;i<4;i++){
            firstBead.dir=i;
            q.add(firstBead);
        }

        System.out.println("연산을 시작합니다.");

        while(!q.isEmpty()){
            Beads curBeads=q.poll();
            Boolean redDown=false;
            Boolean blueDown=false;

            int curRx=curBeads.Rx;
            int curRy=curBeads.Ry;
            int curBx=curBeads.Bx;
            int curBy=curBeads.By;
            int curCnt=curBeads.cnt;
            int curDir=curBeads.dir;

            int redCnt=0;
            int blueCnt=0; // 뒤로 보낼 거 비교

            if(visited[curRx][curRy][curBx][curBy][curDir]==true){
                System.out.println(curBeads+"이미 방문해서 패스");
                continue;
            }

            visited[curRx][curRy][curBx][curBy][curDir]=true;
            
            System.out.println("연산중 포맷:"+curBeads);
            if(curCnt>10) continue;

            while(true){ // Red bead
                curRx+=dx[curDir];
                curRy+=dy[curDir];
                redCnt+=1;

                if(board[curRx][curRy]==8){
                    curRx-=dx[curDir];
                    curRy-=dy[curDir];
                    redCnt-=1;
                    break;
                }

                if(board[curRx][curRy]==0){
                    redDown=true;
                    break;
                }
            }

            while(true){ // Blue bead
                curBx+=dx[curDir];
                curBy+=dy[curDir];
                blueCnt+=1;

                if(board[curBx][curBy]==8){
                    curBx-=dx[curDir];
                    curBy-=dy[curDir];
                    blueCnt-=1;
                    break;
                }

                if(board[curBx][curBy]==0){
                    blueDown=true;
                    break;
                }
            }

            System.out.println("연산 후 Red bead:"+curRx+","+curRy);
            System.out.println("연산 후 Blue bead:"+curBx+","+curBy);
            
            if(redDown){
                if(blueDown){
                    if(redCnt>blueCnt){
                        System.out.println("성공. 동시이나 빨강이 빠름:"+curCnt+1);
                        calcCount=Math.min(calcCount, curCnt+1);
                    }
                    continue;
                }else{
                    System.out.println("성공");
                    calcCount=Math.min(calcCount, curCnt+1);
                    continue;
                }
            }

            System.out.println("redDown:"+redDown+", blueDown:"+blueDown);
            for(int i=0;i<4;i++){
                //System.out.println("경우의 수 반복");
                if((curDir+i)>3){
                    q.add(new Beads(curRx, curRy, curBx, curBy, i, curCnt+1));
                }else{
                    q.add(new Beads(curRx, curRy, curBx, curBy, curDir+i, curCnt+1));
                }
            }
        }

        if(calcCount==Integer.MAX_VALUE){
            bw.write("-1\n");
        }else{
            bw.write(calcCount+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

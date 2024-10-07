import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출2_13460 {
    static class Beads implements Cloneable{
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

        @Override
        protected Beads clone() throws CloneNotSupportedException {
            // TODO Auto-generated method stub
            return (Beads)super.clone();
        }
    }
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir");
        BufferedReader br=new BufferedReader(new FileReader(path+"/input13460.txt"));   
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
            Beads tmpBeads=firstBead.clone();
            tmpBeads.dir=i;
            q.add(tmpBeads);
            System.out.println("초기 연산 큐:"+tmpBeads);
        }

        System.out.println("큐 맨앞:"+q.peek());

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
            int blueCnt=0;

            if(visited[curRx][curRy][curBx][curBy][curDir]==true){
                System.out.println(curBeads+"이미 방문해서 패스");
                continue;
            }

            visited[curRx][curRy][curBx][curBy][curDir]=true;
            System.out.println(curBeads+"방문 처리");
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
            
            if(blueDown){ // 걍 실패
                continue;
            }

            if(redDown){
                if(blueDown){ // 한 턴 안에 두 구슬이 다 빠진 경우
                    // calcCount=Math.min(calcCount, curCnt+1);
                    continue;
                }else{
                    System.out.println("성공");
                    calcCount=Math.min(calcCount, curCnt+1);
                    continue;
                }
            }

            // 같은 칸일 경우 뒤로 보낼 구슬을 결정한다. 근데 이동한 칸 똑같으면..?? < 가능한 케이스인ㅇ가
            if(curRx==curBx & curRy==curBy){ // 여태 이동한 칸 비교
                if(redCnt>blueCnt){ // 빨강이 뒤로
                    curRx-=dx[curDir];
                    curRy-=dy[curDir];
                }else if(redCnt<blueCnt){ // 파랑이 뒤로
                    curBx-=dx[curDir];
                    curBy-=dy[curDir];
                }
            }

            System.out.println("redDown:"+redDown+", blueDown:"+blueDown);
            for(int i=1;i<4;i++){
                //System.out.println("경우의 수 반복");
                if((curDir+i)>3){
                    System.out.println("dir add:"+(curDir+i-4));
                    q.add(new Beads(curRx, curRy, curBx, curBy, curDir+i-4, curCnt+1));
                }else{
                    System.out.println("dir add:"+(curDir+i));
                    q.add(new Beads(curRx, curRy, curBx, curBy, curDir+i, curCnt+1));
                }
            }
        }

        if(calcCount==Integer.MAX_VALUE || calcCount>10){
            bw.write("-1\n");
        }else{
            bw.write(calcCount+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

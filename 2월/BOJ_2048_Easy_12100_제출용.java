import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2048_Easy_12100_제출용 {
    static class Calc{
        int[][] map;
        int dir;
        int cnt;
        int dirCnt; // 한 방향으로 끝까지 병합

        public Calc(int[][] map, int dir, int cnt, int dirCnt){
            this.map=map;
            this.dir=dir;
            this.cnt=cnt;
            this.dirCnt=dirCnt;
        }
    }

    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/";
        BufferedReader br=new BufferedReader(new FileReader("input12100.txt"));
        // BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st; 

        int N=Integer.parseInt(br.readLine());
        int[][] map=new int[N][N];
        Queue<Calc> q=new LinkedList<>();

        int[] dx={-1,1,0,0};
        int[] dy={0,0,-1,1}; // 상하좌우 delta
        int maxBlockValue=2;

        for(int n=0;n<N;n++){
            st=new StringTokenizer(br.readLine());
            for(int m=0;m<N;m++){
                map[n][m]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<4;i++){
            q.add(new Calc(map, i, 0,0));
        }

        while(!q.isEmpty()){
            Calc calcTmp=q.poll();
            if(calcTmp.cnt==2){
                continue;
            }

            int[][] originMap=calcTmp.map;
            int[][] calcMap=new int[N][N];
            boolean[][] integrated=new boolean[N][N]; // 덮어쓰기 처리

            // shift
            switch(calcTmp.dir){
                case 0:
                    for(int y=0;y<N;y++){
                        int stackX=0;
                        for(int x=0;x<N;x++){
                            if(originMap[x][y]!=0){
                                if(calcMap[stackX][y]==originMap[x][y]){
                                    calcMap[stackX++][y]*=2;  // 합치고 다음칸        
                                    // 합쳤을 때 이전 칸과 병합이 가능한 지가 관건이 됨              
                                }else{
                                    if(calcMap[stackX][y]!=0){
                                        calcMap[++stackX][y]=originMap[x][y];
                                    }else{
                                        calcMap[stackX][y]=originMap[x][y];
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 1:
                    for(int y=0;y<N;y++){
                        int stackX=N-1;
                        for(int x=N-1;x>=0;x--){
                            if(originMap[x][y]!=0){
                                if(calcMap[stackX][y]==originMap[x][y]){
                                    calcMap[stackX--][y]*=2;
                                }else{
                                    if(calcMap[stackX][y]!=0){
                                        calcMap[--stackX][y]=originMap[x][y];
                                    }else{
                                        calcMap[stackX][y]=originMap[x][y];
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    for(int x=0;x<N;x++){
                        int stackY=0;
                        for(int y=0;y<N;y++){
                            if(originMap[x][y]!=0){
                                if(calcMap[x][stackY]==originMap[x][y]){
                                    calcMap[x][stackY++]*=2; // 합치면 stk 증가하면 됨(cascade 되지 않음)
                                }else{ // 합칠 아이템이 아닐 때
                                    calcMap[x][stackY]=originMap[x][y];
                                    // 합치지 않았을 때 stk에 그대로 넣어 버려서 덮어쓰기 문제 생김
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    for(int x=0;x<N;x++){
                        int stackY=N-1;
                        for(int y=N-1;y>=0;y--){
                            if(originMap[x][y]!=0){
                                if(calcMap[x][stackY]==originMap[x][y]){ // 합치기
                                    integrated[x][stackY]=true;
                                    calcMap[x][stackY--]*=2;
                                }else{
                                    calcMap[x][stackY]=originMap[x][y];
                                }
                            }
                        }
                    }
                    break;
            }

            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    if(maxBlockValue<calcMap[x][y]){
                        maxBlockValue=calcMap[x][y];
                    }
                }
            }

            // next calc add
            for(int i=0;i<4;i++){
                if(calcTmp.dir==i){
                    if(calcTmp.dirCnt!=(N/2)){ // 해당 방향으로 끝까지 병합하지 않은 경우
                        q.add(new Calc(calcMap, i, calcTmp.cnt, calcTmp.dirCnt+1)); // 끝까지 병합 시도
                    }else{
                        continue; // 해당 방향 끝남
                    }
                }
                q.add(new Calc(calcMap, i, calcTmp.cnt+1, 0)); // 다음 연산
            }
        }

        bw.write(maxBlockValue+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

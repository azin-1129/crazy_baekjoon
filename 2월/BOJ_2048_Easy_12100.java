import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2048_Easy_12100 {
    static class Calc{
        int[][] map;
        int dir;
        int cnt;

        public Calc(int[][] map, int dir, int cnt){
            this.map=map;
            this.dir=dir;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"/";
        BufferedReader br=new BufferedReader(new FileReader("input12100.txt"));
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
            q.add(new Calc(map, 0, i));
        }

        while(!q.isEmpty()){
            Calc calcTmp=q.poll();
            if(calcTmp.cnt==5){
                // System.out.println("5번을 초과한 연산 시도. 중지");
                continue;
            }
            
            System.out.println("현재 연산중인 dir:"+calcTmp.dir);

            int[][] originMap=calcTmp.map;
            int[][] calcMap=new int[N][N];

            System.out.println("연산 전 배열");
            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    System.out.print(originMap[x][y]+" ");
                }
                System.out.println();
            }

            // shift
            switch(calcTmp.dir){
                case 0:
                    for(int y=0;y<N;y++){
                        int stackX=0;
                        for(int x=0;x<N;x++){
                            if(originMap[x][y]!=0){
                                if(calcMap[stackX][y]==originMap[x][y]){
                                    calcMap[stackX++][y]*=2;
                                }else{
                                    if(stackX==N){
                                        continue;
                                    }
                                    calcMap[stackX][y]=originMap[x][y];
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
                                    if(stackX==0){
                                        continue;
                                    }
                                    calcMap[stackX][y]=originMap[x][y];
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
                                    calcMap[x][stackY++]*=2;
                                }else{
                                    if(stackY==N){
                                        continue;
                                    }
                                    calcMap[x][stackY]=originMap[x][y];
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
                                    calcMap[x][stackY--]*=2;
                                }else{
                                    if(stackY==0){
                                        continue;
                                    }
                                    calcMap[x][stackY]=originMap[x][y]; // 붙이기
                                }
                            }
                        }
                    }
                    break;
            }

            // maxBlockValue compare
            System.out.println("연산 후 배열 결과");

            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    if(maxBlockValue<calcMap[x][y]){
                        maxBlockValue=calcMap[x][y];
                    }
                    System.out.print(calcMap[x][y]+" ");
                }
                System.out.println();
            }

            // next calc add
            for(int i=0;i<4;i++){
                if(calcTmp.dir==i) continue;

                q.add(new Calc(calcMap, i, calcTmp.cnt+1));
            }
        }

        bw.write(maxBlockValue+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2048_Easy_12100_제출용 {
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
                continue;
            }

            int[][] originMap=calcTmp.map;
            int[][] calcMap=new int[N][N];

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
                                if(calcMap[x][stackY]==originMap[x][y]){
                                    calcMap[x][stackY--]*=2;
                                }else{
                                    calcMap[x][stackY]=originMap[x][y];
                                }
                            }
                        }
                    }
                    break;
            }

            // maxBlockValue compare

            for(int x=0;x<N;x++){
                for(int y=0;y<N;y++){
                    if(maxBlockValue<calcMap[x][y]){
                        maxBlockValue=calcMap[x][y];
                    }
                }
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

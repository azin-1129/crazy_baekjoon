package Code;

import java.util.*;
import java.io.*;

class BOJ_20057{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=20057;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        // 달팽이 규칙: 1칸, 1칸, 2칸, 2칸, 3칸, 3칸 ...
        // range를 벗어났을 경우 result에 더하기
        // 달팽이 중, 좌표가 0, 0이면 아웃

        // 모래 테이블 4개 있어야겠지?

        // 근데 모래 테이블 적용을 어떻게 해야되지?
        // -2, -2를 한 뒤(모래 테이블의 0, 0과 맞춤)
        // 가로세로 4씩 돌면서 범위 밖인지 아닌지 판단해서 적용..

        int N=Integer.parseInt(br.readLine());
        int[][] grid=new int[N][N];


        for(int i=0;i<N;i++){
            grid[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int snailCount=2*(N-1)+1;
        int moveCount=0;
        int dir=0;

        // 좌, 하, 우, 상
        int[] dx={0, 1, 0, -1};
        int[] dy={-1, 0, 1, 0};

        double[][][] sandPalette=new double[4][5][5];
        sandPalette[0]=new double[][]{
            {0, 0, 0.02, 0, 0},
            {0, 0.1, 0.07, 0.01, 0},
            {0.05, 0, 0, 0, 0},
            {0, 0.1, 0.07, 0.01, 0},
            {0, 0, 0.02, 0, 0}
        };
        sandPalette[1]=new double[][]{
            {0, 0, 0, 0, 0},
            {0,0.01, 0, 0.01, 0},
            {0.02, 0.07, 0, 0.07, 0.02},
            {0, 0.1, 0, 0.1, 0},
            {0, 0, 0.05, 0, 0}
        };
        sandPalette[2]=new double[][]{
            {0, 0, 0.02, 0, 0},
            {0, 0.01, 0.07, 0.1, 0},
            {0, 0, 0, 0, 0.05},
            {0, 0.01, 0.07, 0.1, 0},
            {0, 0, 0.02, 0, 0}
        };
        sandPalette[3]=new double[][]{
            {0, 0, 0.05, 0, 0},
            {0, 0.1, 0, 0.1, 0},
            {0.02, 0.07, 0, 0.07, 0.02},
            {0, 0.01, 0, 0.01, 0},
            {0, 0, 0, 0, 0}
        };

        // 이동 마다 모래 테이블을 방향대로 적용하기
        // 가운데 좌표(토네이도 시작점)
        int cx=N/2;
        int cy=N/2;        
        int result=0;
        int dxShift=1;
        int curve=0;
        while(true){
            if(cx==0 & cy==0){
                break;
            }

            if(dir==4){
                dir=0;
            }

            // 앞쪽의 모래 y 좌표
            int nx=cx+dx[dir];
            int ny=cy+dy[dir];

            // System.out.println("현 dir: "+dir);
            // System.out.println("y 좌표:"+nx+", "+ny);
            // System.out.println("x 좌표:"+cx+", "+cy);

            // 흩날리고 남은 모래를 측정해야함
            int sprayedSand=0;
            // 모래 테이블 적용
            for(int x=nx-2;x<=nx+2;x++){ // 1~5
                for(int y=ny-2;y<=ny+2;y++){ // -1~3
                    int spray=(int)Math.floor((((double)grid[nx][ny])*sandPalette[dir][x-(nx-2)][y-(ny-2)]));
                    // System.out.println("y의 Sand:"+grid[nx][ny]+", spray 값:"+spray);
                    // 범위 밖
                    if(x<0 || y<0 || x>=N || y>=N){
                        // System.out.println(spray+"만큼 빠져 나갑니다.");
                        result+=spray;
                    }else{
                        // 범위 안
                        grid[x][y]+=spray;
                        cx=nx;
                        cy=ny;
                    }
                    sprayedSand+=spray;
                }
            }
            
            // alpha 좌표
            int ax=nx+dx[dir];
            int ay=ny+dy[dir];

            if(ax<0 || ay<0 || ax>=N || ay>=N){
                // System.out.println((grid[nx][ny]-sprayedSand)+" <- grid 밖 alpha 값");
                result+=(grid[nx][ny]-sprayedSand); // 범위 밖으로 삐져나감
            }else{
                grid[ax][ay]+=(grid[nx][ny]-sprayedSand);
            }

            grid[nx][ny]=0;

            // System.out.println("토네이도가 한 번 지나갔습니다.");
            // for(int idx=0;idx<N;idx++){
            //     System.out.println(Arrays.toString(grid[idx]));
            // }
            moveCount+=1;
            // 1, 1, 2, 2, 3, 3, ...
            if(moveCount==dxShift){ // 한 화살표를 다 채웠을 때
                if(curve==1){ // 크기가 더 큰 화살표로 바꾸기
                    dxShift+=1;
                    dir+=1;
                    moveCount=0;
                    curve=0;
                }else{ // 크기가 그대로인 화살표로 바꾸기
                    dir+=1;
                    curve+=1;
                    moveCount=0;
                }
            }
        }

        System.out.println(result);
        br.close();
    }
}
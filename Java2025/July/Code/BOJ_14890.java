package Code;

import java.util.*;
import java.io.*;

class BOJ_14890{
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14890;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        map=new int[N][N];
        int result=0;

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // 열 체크
        for(int y=0;y<N;y++){
            System.out.println(y+"번째 열 체크중");
            int x=0;
            while(true){
                int nx=x+1;
                if(outRange(nx, y)){
                    System.out.println("다음 칸은 아웃레인지입니다. 길을 모두 건넜습니다.");
                    result+=1;
                    break;
                }
                int diff=Math.abs(map[nx][y]-map[x][y]);
                System.out.println(x+"칸과 "+nx+"칸의 높이 차는 :"+diff);
                if(diff==0){
                    if(outRange(nx, y)){
                        System.out.println("한칸 이동했으나, 범위를 벗어납니다.");
                        result+=1;
                        break;
                    }
                    System.out.println("한칸 이동합니다.");
                    x=nx;
                }else if(diff==1){
                    System.out.println("경사로를 놓으려 합니다.");
                    if(outRange(x+L, y)){
                        System.out.println("경사로 놓기가 불가능합니다.");
                        break;
                    }
                    if(checkFlat(x+1, y)){
                        System.out.println("경사로를 놓습니다.");
                        if(Math.abs(map[nx+L][y]-map[nx+L-1][y])>0){
                            System.out.println("경사로를 놓으면 더이상 진입할 수 없습니다.");
                            break;
                        }
                        x=nx+L;
                        System.out.println(x+"칸으로 이동했습니다.");
                        if(outRange(x, y)){
                            System.out.println("경사로를 지나가면 아웃이기 때문에 성공입니다.");
                            result+=1;
                            break;
                        }
                    }else{
                        System.out.println("이 경로는 경사로를 놓을 수 없습니다.");
                        break;
                    }
                }else{
                    System.out.println("이 경로는 진입할 수 없습니다.");
                    break;
                }
            }
            System.out.println();
        }

        // 행 체크
        // for(int x=0;x<N;x++){
        //     int y=0;
        //     while(true){
        //         int ny=y+1;
        //         int diff=Math.abs(map[x][ny]-map[x][y]);
        //         if(diff==0){
        //             if(outRange(x, ny)){
        //                 result+=1;
        //                 break;
        //             }
        //         }else if(diff==1){
        //             if(outRange(x, y+L)){
        //                 break;
        //             }
        //             if(checkFlat(ny, x)){
        //                 y=ny+L;
        //                 if(outRange(x, y)){
        //                     result+=1;
        //                     break;
        //                 }
        //             }
        //         }else{
        //             break;
        //         }
                
        //     }
        // }
        System.out.println(result);
        br.close();
    }
    static boolean outRange(int x, int y){
        if(x<0 || y<0 || x>=N || y>=N){
            return true;
        }else{
            return false;
        }
    }
    static boolean checkFlat(int x, int y){
        int height=map[x][y];
        for(int i=(x+1);i<(x+L);i++){
            if(height!=map[i][y]){
                return false;
            }
        }
        return true;
    }
}
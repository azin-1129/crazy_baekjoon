package Code;

import java.util.*;

class Pgm_1003_1 {
    public static void main(String[] args) throws Exception{

        int[][] board=new int[][]{
            {5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5}
        };
        int[][] skill=new int[][]{
            {1, 0, 0, 3, 4, 4},
            {1, 2, 0, 2, 3, 2},
            {2, 1, 0, 3, 1, 2},
            {1, 0, 1, 3, 3, 1}
        };

        System.out.println(solution(board, skill));
    }
    public static int solution(int[][] board, int[][] skill) {
        int lengthX=board.length;
        int lengthY=board[0].length;
        int count=0;
        int[][] shiftValues=new int[lengthX+1][lengthY+1];
        //  0     1   2   3   4   5
        // {type, x1, y1, x2, y2, degree}
        // 1. skill 기준으로, 누적합 배열 작성
        for(int[] sk : skill){
            // 회복(1=공격, 2=회복)
            shiftValues[sk[1]][sk[2]]+=(sk[0]==1? -sk[5] : sk[5]); 
            shiftValues[sk[1]][sk[4]+1]+=(sk[0]==1? sk[5] : -sk[5]);
            shiftValues[sk[3]+1][sk[2]]+=(sk[0]==1? sk[5] : -sk[5]);
            shiftValues[sk[3]+1][sk[4]+1]+=(sk[0]==1? -sk[5] : sk[5]);
        }

        // 2. 행 방향으로 시프트
        for(int x=0;x<lengthX;x++){
            for(int y=1;y<lengthY;y++){
                shiftValues[x][y]+=shiftValues[x][y-1];
            }
        }

        // 3. 열 방향으로 시프트
        for(int y=0;y<lengthY;y++){
            for(int x=1;x<lengthX;x++){
                shiftValues[x][y]+=shiftValues[x-1][y];
            }
        }

        // 4. 누적합 배열과 원본 배열 합산
        for(int x=0;x<lengthX;x++){
            for(int y=0;y<lengthY;y++){
                board[x][y]+=shiftValues[x][y];
                if(board[x][y]>0){
                    count++;
                }
            }
        }

        return count;
    }
    static void print2D(int[][] arr){
        for(int x=0;x<arr.length;x++){
            System.out.println(Arrays.toString(arr[x]));
        }
    }
}
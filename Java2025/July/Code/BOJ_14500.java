package Code;

import java.util.*;
import java.io.*;

class BOJ_14500{
    static int[][][][][] tetrominos=
    {
        {
            {
                { {0,0}, {0,1}, {0,2}, {0,3} },
                { {0,0}, {1,0}, {2,0}, {3,0} },
                { {0,0}, {0,1}, {0,2}, {0,3} },
                { {0,0}, {1,0}, {2,0}, {3,0} }
            },
            {}
        },
        {
            {
                { {0,0}, {0,1}, {1,0}, {1,1} },
            },
            {}
        },
        {
            {
                { {0,0}, {0,1}, {0,2}, {1,1} },
                { {0,0}, {1,0}, {2,0}, {1, 1} },
                { {0,0}, {1,-1}, {1,0}, {1,1} },
                { {0,0}, {1,0}, {1,-1}, {2,0} }
            },
            {}
        },
        {
            {
                { {0,0}, {1,0}, {2,0}, {2,1} },
                { {0,0}, {0,1}, {0,2}, {1,0} },
                { {0,0}, {0,1}, {1,1}, {2,1} },
                { {0,2}, {1,0}, {1,1}, {1,2} }
            },
            {
                { {0,0}, {1,0}, {2,0}, {2,-1} },
                { {0,-2}, {0,-1}, {0,0}, {1,0} },
                { {0,0}, {0,-1}, {1,-1}, {2,-1} },
                { {0,-2}, {1,-2}, {1,-1}, {1,0} }
            }
        },
        {
            {
                { {0,0}, {1,0}, {1,1}, {2,1} },
                { {0,1}, {0,2}, {1,0}, {1,1} },
                { {0,0}, {1,0}, {1,1}, {2,1} },
                { {0,1}, {0,2}, {1,0}, {1,1} }
            },
            { 
                { {0,0}, {1,0}, {1,-1}, {2,-1} },
                { {0,-2}, {0,-1}, {1,-1}, {1,0} },
                { {0,0}, {1,0}, {1,-1}, {2,-1} },
                { {0,-2}, {0,-1}, {1,-1}, {1,0} }
            }
        }
    };
    static int[][] paper;
    static int N, M, result;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=14500;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        paper=new int[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<M;j++){
                paper[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // 도형 1. 대칭X, 회전O
        for(int i=0;i<4;i++){
            scoring(tetrominos[0][0][i]);
        }
        // 도형 2. 대칭X, 회전X
        scoring(tetrominos[1][0][0]);
        // 도형 3. 대칭X, 회전O
        for(int i=0;i<4;i++){
            scoring(tetrominos[2][0][i]);
        }
        // 도형 4. 대칭O, 회전O
        for(int i=0;i<4;i++){
            scoring(tetrominos[3][0][i]);
            scoring(tetrominos[3][1][i]);
        }
        // 도형 5. 대칭O, 회전O
        for(int i=0;i<4;i++){
            scoring(tetrominos[4][0][i]);
            scoring(tetrominos[4][1][i]);
        }
        System.out.println(result);
        br.close();
    }
    static void scoring(int[][] tetromino){
        for(int x=0;x<N;x++){
            for(int y=0;y<M;y++){
                int score=0;
                for(int z=0;z<4;z++){
                    int tx=x+tetromino[z][0];
                    int ty=y+tetromino[z][1];

                    if(tx<0 || ty<0 || tx>=N || ty>=M){
                        score=0;
                        break;
                    }else{
                        score+=paper[tx][ty];
                    }
                }
                result=Math.max(result, score);
            }
        }
    }
}
package Code;

import java.util.*;
import java.io.*;

class BOJ_10709{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10709;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int[] inputs=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int H=inputs[0];
        int W=inputs[1];

        int[][] weatherInfo=new int[H][W];
        int[][] result=new int[H][W];

        // 전부 -1로 초기화
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                result[i][j]=-1;
            }
        }

        for(int i=0;i<H;i++){
            char[] info=br.readLine().toCharArray();
            for(int j=0;j<W;j++){
                char temp=info[j];

                if(temp=='c'){
                    weatherInfo[i][j]=1;
                }
            }
        }
        
        for(int i=0;i<H;i++){
            int weight=0;
            int isCloudy=0;
            for(int j=0;j<W;j++){
                if(weatherInfo[i][j]==1){ // 1의 경우 구름
                    result[i][j]=0;
                    isCloudy=1;
                    weight=0;
                }else{
                    if(isCloudy>0){
                        result[i][j]=(++weight);
                    }
                }
            }
        }
        // 구름 좌표는 0으로 하고, 그 다음 구름을 만날 때까지 1씩 누적해 주면 될 듯

        for(int i=0;i<H;i++){
            System.out.println(Arrays.toString(result[i]).replaceAll("[\\[\\],]",""));
        }

        br.close();
    }
}
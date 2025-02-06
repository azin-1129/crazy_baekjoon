package Code;

import java.io.*;
import java.util.*;

public class Softeer_진정한_효도 {
    static final int GROUND_RANGE=3;
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="진정한_효도";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int[][] ground=new int[GROUND_RANGE][GROUND_RANGE];

        for(int i=0;i<GROUND_RANGE;i++){
            ground[i]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 가로 카운트
        int[][] horizontalCount=new int [GROUND_RANGE][GROUND_RANGE+1];
        for(int x=0;x<GROUND_RANGE;x++){
            for(int y=0;y<GROUND_RANGE;y++){
                horizontalCount[x][ground[x][y]]+=1;
            }
        }

        // 가로 최소 비용 idx 계산
        List<Integer> maxXIdxs=new ArrayList<>();
        List<Integer> horizontalMinValues=new ArrayList<>();

        int horizontalMax=0;
        int maxXIdx=0;
        int horizontalMinValue=0;
        for(int x=0;x<GROUND_RANGE;x++){
            for(int y=1;y<GROUND_RANGE+1;y++){
                if(horizontalCount[x][y]>=horizontalMax){
                    horizontalMax=horizontalCount[x][y];
                    maxXIdx=x;
                    horizontalMinValue=y;

                    maxXIdxs.add(maxXIdx);
                    horizontalMinValues.add(horizontalMinValue);
                }
            }
        }
        
        // 가로 최소 비용 계산
        int horizontalValue=Integer.MAX_VALUE;
        for(int i=0;i<maxXIdxs.size();i++){
            int temp=0;

            maxXIdx=maxXIdxs.get(i);
            horizontalMinValue=horizontalMinValues.get(i);
            for(int y=0;y<GROUND_RANGE;y++){
                if(ground[maxXIdx][y]!=horizontalMinValue){
                    int big;
                    int small;
                    if(ground[maxXIdx][y]>horizontalMinValue){
                        big=ground[maxXIdx][y];
                        small=horizontalMinValue;
                    }else{
                        big=horizontalMinValue;
                        small=ground[maxXIdx][y];
                    }
                    temp+=(big-small);
                }
            }

            horizontalValue=Math.min(horizontalValue, temp);
        }

        // 세로 카운트
        int[][] verticalCount=new int [GROUND_RANGE][GROUND_RANGE+1];
        for(int y=0;y<GROUND_RANGE;y++){
            for(int x=0;x<GROUND_RANGE;x++){
                verticalCount[y][ground[x][y]]+=1;
            }
        }

        // 세로 최소 비용 idx 계산
        List<Integer> maxYIdxs=new ArrayList<>();
        List<Integer> verticalMinValues=new ArrayList<>();

        int verticalMax=0;
        int maxYIdx=0;
        int verticalMinValue=0;
        for(int y=0;y<GROUND_RANGE;y++){
            for(int x=1;x<GROUND_RANGE+1;x++){
                if(verticalCount[y][x]>verticalMax){
                    verticalMax=verticalCount[y][x];
                    maxYIdx=y;
                    verticalMinValue=x;
                    maxYIdxs.add(maxYIdx);
                    verticalMinValues.add(verticalMinValue);
                }
            }
        }

        // 세로 최소 비용 계산
        int verticalValue=Integer.MAX_VALUE;

        for(int i=0;i<maxYIdxs.size();i++){
            int temp=0;

            maxYIdx=maxYIdxs.get(i);
            verticalMinValue=verticalMinValues.get(i);
            for(int x=0;x<GROUND_RANGE;x++){
                if(ground[x][maxYIdx]!=verticalMinValue){
                    int big;
                    int small;
                    if(ground[x][maxYIdx]>verticalMinValue){
                        big=ground[x][maxYIdx];
                        small=verticalMinValue;
                    }else{
                        big=verticalMinValue;
                        small=ground[x][maxYIdx];
                    }
                    temp+=(big-small);
                }
            }
            verticalValue=Math.min(verticalValue, temp);
        }

        System.out.println(Math.min(horizontalValue, verticalValue));

        br.close();
    }   
}

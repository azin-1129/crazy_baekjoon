package Code;

import java.util.*;
import java.io.*;

class BOJ_1026{
    static int MAX_VAL=10001;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1026;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        int[] aNumbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] bNumbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(aNumbers);

        // A의 큰 원소*B의 작은 원소

        List<Integer> bNumberList=new ArrayList<>();

        for(int i=0;i<N;i++){
            bNumberList.add(bNumbers[i]);
        }

        int result=0;
        for(int i=N-1;i>=0;i--){
            int minResult=MAX_VAL; // 임시 최댓값
            int minIdx=0;
            for(int j=0;j<bNumberList.size();j++){
                int calcTemp=aNumbers[i]*bNumberList.get(j);

                if(minResult>calcTemp){
                    minResult=calcTemp;
                    minIdx=j;
                }
            }
            bNumberList.remove(minIdx);
            result+=minResult;
        }

        System.out.println(result);

        br.close();
    }

    // #3
    // A: 100, 39, 31, 26, 15, 5, 3, 0, 0
    // B:   1,  2,  3,  4,  5, 9,11,12, 13
    // 100+78+93+104+75+45+33+0+0
}
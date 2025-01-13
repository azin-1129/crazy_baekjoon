package Code;

import java.util.*;
import java.io.*;

class BOJ_1138{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1138;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        int N=Integer.parseInt(br.readLine());

        int[] people=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] result=new int[N];

        for(int i=0;i<N;i++){
            int index=0;

            while(true){
                if(result[index]==0){
                    if(people[i]==0){
                        result[index]=(i+1);
                        break;
                    }else{
                        people[i]-=1;
                    }
                }

                index+=1; // result 원소값이 0이 아니다.
            }
        }

        System.out.println(Arrays.toString(result).replaceAll("[\\[\\],]", ""));

        br.close();
    }

    // 7: 0명, 5: 2명
}
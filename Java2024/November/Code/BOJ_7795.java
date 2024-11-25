package Code;

import java.util.*;
import java.io.*;

class BOJ_7795{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=7795;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        int T=Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st=new StringTokenizer(br.readLine(), " ");

            int A=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());

            int[] weightA=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] weightB=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            Arrays.sort(weightA);
            Arrays.sort(weightB);

            int result=0;

            for(int i=0;i<weightA.length;i++){
                result+=binarySearch(weightB, weightA[i]);
            }

            sb.append(result+"\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        
        br.close();
    }
    static public int binarySearch(int[] arr, int target){
        int left=0;
        int right=arr.length-1;
        int idx=0;

        while(left<=right){
            int middle=(left+right)/2;

            if(arr[middle]<target){
                left=middle+1;
                idx=middle+1;
            }else{
                right=middle-1;
            }
        }
        return idx;
    }
}
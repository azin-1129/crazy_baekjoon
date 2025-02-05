package Code;

import java.io.*;
import java.util.*;

public class Softeer_징검다리 {
    static int[] lis;
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="징검다리";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int N=Integer.parseInt(br.readLine());
        lis=new int[N];
        int[] arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // LIS
        lis[0]=arr[0];
        int i=0;
        int j=1;

        while(j<N){
            if(lis[i]<arr[j]){
                lis[++i]=arr[j];
            }else{
                int idx=binarySearch(0, i, arr[j]);
                lis[idx]=arr[j];
            }
            ++j;
        }

        System.out.println(Arrays.toString(lis));
        System.out.println(i+1);

        br.close();
    }
    static int binarySearch(int left, int right, int target){
        while(left<right){
            int mid=(left+right)/2;

            if(lis[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return right;
    }
}

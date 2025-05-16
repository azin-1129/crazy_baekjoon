package Code;

import java.util.*;
import java.io.*;

class BOJ_2752{
    static int ARR_SIZE=3;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int[] arr=new int[ARR_SIZE];
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");

        for(int i=0;i<ARR_SIZE;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]",""));
        br.close();
    }
}
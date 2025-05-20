package Code;

import java.util.*;
import java.io.*;

class BOJ_3273{
    public static void main(String[] args) throws Exception{
        // BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=3273;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int n=Integer.parseInt(br.readLine());
        int[] nums=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        int x=Integer.parseInt(br.readLine());

        int front=0;
        int end=n-1;

        Arrays.sort(nums);

        int result=0;
        while(front<end){
            int current=nums[front]+nums[end];
            if(current==x){
                result+=1;
                front+=1;
                end-=1;
            }else if(current>x){
                end-=1;
            }else{
                front+=1;
            }
        }

        System.out.println(result);
        br.close();
    }
}
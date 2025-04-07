package Code;

import java.util.*;
import java.io.*;

class BOJ_10988{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=10988;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        String[] input=br.readLine().split("");
        int left=0;
        int right=input.length-1;

        int flag=1;
        while(left<right){
            if(input[left].equals(input[right])){
                left++;
                right--;
                continue;
            }else{
                flag=0;
                break;
            }
        }

        System.out.println(flag);
        br.close();
    }
}
package Code;

import java.util.*;
import java.io.*;

class BOJ_6603{
    static int[] nums, result;
    static int k;
    static int ITEM_COUNT=6;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=6603;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        while(true){
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            k=Integer.parseInt(st.nextToken());
            if(k==0){
                break;
            }
            nums=new int[k];
            result=new int[ITEM_COUNT];
            for(int i=0;i<k;i++){
                nums[i]=Integer.parseInt(st.nextToken());
            }
            comb(0, 0);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        
        br.close();
    }
    static void comb(int start, int depth){
        if(depth>=ITEM_COUNT){
            for(int i=0;i<ITEM_COUNT;i++){
                sb.append(result[i]+" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            return;
        }
        
        for(int i=start;i<k;i++){
            result[depth]=nums[i];
            comb(i+1, depth+1);
        }
    }
}
package Code;

import java.util.*;
import java.io.*;

class BOJ_4948{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=4948;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        boolean[] isDeleted=new boolean[246913];
        for(int x=2;x<=246912;x++){
            if(isDeleted[x]){
                continue;
            }
            for(int y=2;(x*y)<=246912;y++){
                if(isDeleted[(x*y)]){
                    continue;
                }else{
                    isDeleted[(x*y)]=true;
                }
            }
        }

        System.out.println();
        StringBuilder sb=new StringBuilder();
        while(true){
            int n=Integer.parseInt(br.readLine());
            if(n==0){
                break;
            }

            int result=0;
            for(int i=(n+1);i<=(2*n);i++){
                if(!isDeleted[i]){
                    result+=1;
                }
            }
            sb.append(result+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
    }
}
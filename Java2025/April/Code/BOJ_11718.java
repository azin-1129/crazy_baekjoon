package Code;

import java.util.*;
import java.io.*;

class BOJ_11718{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=11718;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        String s;

        while(true){
            s=br.readLine();
            if(s==null){
                break;
            }
            sb.append(s+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);

        br.close();
    }
}
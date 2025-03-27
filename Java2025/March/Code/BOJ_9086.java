package Code;

import java.util.*;
import java.io.*;

class BOJ_9086{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=9086;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb;

        int T=Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            String[] str=br.readLine().split("");
            sb=new StringBuilder();

            sb.append(str[0]);
            sb.append(str[str.length-1]);

            System.out.println(sb);
        }

        br.close();
    }
}
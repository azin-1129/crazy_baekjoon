package Code;

import java.util.*;
import java.io.*;

class BOJ_25314{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=25314;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());

        for(int i=0;i<N/4;i++){
            sb.append("long ");
        }

        sb.append("int");
        System.out.println(sb);
        br.close();
    }
}
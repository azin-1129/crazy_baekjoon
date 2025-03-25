package Code;

import java.io.*;
import java.util.*;

public class BOJ_11382 {
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=0;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine(), " ");
        
        long A=Long.parseLong(st.nextToken());
        long B=Long.parseLong(st.nextToken());
        long C=Long.parseLong(st.nextToken());

        System.out.println(A+B+C);
        br.close();
    }
}

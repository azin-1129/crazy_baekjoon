package Code;

import java.util.*;
import java.io.*;

class BOJ_27323{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=27323;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int A=Integer.parseInt(br.readLine());
        int B=Integer.parseInt(br.readLine());

        System.out.println(A*B);

        br.close();
    }
}
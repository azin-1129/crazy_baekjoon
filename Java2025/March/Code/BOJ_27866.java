package Code;

import java.util.*;
import java.io.*;

class BOJ_27866{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=27866;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        String S=br.readLine();
        int i=Integer.parseInt(br.readLine());

        System.out.println(S.charAt(i-1));
        br.close();
    }
}
package Code;

import java.util.*;
import java.io.*;

class BOJ_2743{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2743;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        String S=br.readLine();

        System.out.println(S.length());
        br.close();
    }
}
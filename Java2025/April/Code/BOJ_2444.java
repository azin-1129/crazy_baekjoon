package Code;

import java.util.*;
import java.io.*;

class BOJ_2444{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2444;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            System.out.print(" ".repeat((N-1)-i));
            System.out.print("*".repeat((2*i)+1));
            System.out.println();
        }
        
        for(int i=1;i<N;i++){
            System.out.print(" ".repeat(i));
            System.out.print("*".repeat((2*(N-1)+1)-(2*i)));
            System.out.println();
        }
        br.close();
    }
}
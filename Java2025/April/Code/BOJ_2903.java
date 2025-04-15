package Code;

import java.util.*;
import java.io.*;

class BOJ_2903{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2903;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        // (1+(2*횟수))^2
        int N=Integer.parseInt(br.readLine());
        // 3, 5, 9 현재점-1만큼 늘어나지?
        // 2+1=3
        // 3+2=5
        // 5+4=9
        int res=2;
        for(int n=0;n<N;n++){
            res+=(res-1);
        }

        System.out.println((int)Math.pow(res,2));
        br.close();
    }
}
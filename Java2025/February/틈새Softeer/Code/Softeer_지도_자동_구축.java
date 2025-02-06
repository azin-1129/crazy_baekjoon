package Code;

import java.io.*;
import java.util.*;

public class Softeer_지도_자동_구축 {
    public static void main(String[] args) throws Exception {
        String filepath=System.getProperty("user.dir")+"/Input/";
        String probName="지도_자동_구축";

        BufferedReader br=new BufferedReader(new FileReader(filepath+probName+".txt"));

        int N=Integer.parseInt(br.readLine());

        int r=2;

        for(int n=0;n<N;n++){
            r+=(r-1);
        }

        System.out.println(r*r);
        
        br.close();
    }
}

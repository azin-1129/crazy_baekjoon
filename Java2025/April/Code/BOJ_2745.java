package Code;

import java.util.*;
import java.io.*;

class BOJ_2745{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2745;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        String N=st.nextToken();
        int NLength=N.length();
        int B=Integer.parseInt(st.nextToken());

        long res=0L;
        for(int i=0;i<NLength;i++){
            int val=N.charAt(i)-'0';
            if(val>=17){
                res+=(val-7)*(Math.pow(B,(NLength-1-i)));
            }else{
                res+=val*(Math.pow(B,(NLength-1-i)));
            }
        }

        System.out.println(res);
        br.close();
    }
}
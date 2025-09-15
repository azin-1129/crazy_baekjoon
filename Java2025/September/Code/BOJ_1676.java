package Code;
import java.io.*;
import java.util.*;

public class BOJ_1676 {
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir");
        int bojNum=1676;
        BufferedReader br=new BufferedReader(new FileReader(path+"\\input\\input"+bojNum+".txt"));

        long N=Long.parseLong(br.readLine());
        
        long result=1;
        int count=0;
        for(long n=N;n>=2;n--){
            result*=n;

            System.out.println("N:"+n);
            System.out.println("result:"+result);
            // 뒤에 0 개수?
            String temp=Long.toString(result);
            for(int i=temp.length()-1;i>=0;i--){
                if(temp.charAt(i)=='0'){
                    count+=1;
                    System.out.println("0이다");
                    result/=10;
                }else{
                    break;
                }
            }
        }

        System.out.println(count);
        br.close();
    }
}

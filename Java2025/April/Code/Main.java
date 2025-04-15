import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        String N=st.nextToken();
        int NLength=N.length();
        int num=Integer.valueOf(N);
        int B=Integer.parseInt(st.nextToken());

        // 10^0*5 + 10^1*7 + ...
        // to 36^0*? + 36^1*? + ...
        StringBuilder sb=new StringBuilder();
        boolean flag=false;
        for(int i=29;i>=0;i--){
            int op=(int)Math.pow(B,i);
            if(num<op){
                if(flag){
                    sb.append('0');
                }
                continue;
            }
            flag=true;
            int val=num/op;
            num%=op;
            if(val>=10){
                sb.append((char)(val+55));
            }else{
                sb.append(val);
            }
        }

        System.out.println(sb);
        br.close();
    }
}
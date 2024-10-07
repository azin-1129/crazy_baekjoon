package Java2024.October;

import java.util.Scanner;

public class ZOAC4 {
    public static void main(String[] args) throws Exception{
        // String path=System.getProperty("user.dir")+"/2024/10월/";
        Scanner sc=new Scanner(System.in);

        int H=sc.nextInt();
        int W=sc.nextInt();
        int N=sc.nextInt();
        int M=sc.nextInt();

        int Wremain=W%(1+M);
        int Hremain=H%(1+N);

        int result=((H-Hremain)*(W-Wremain)) / ((1+M)*(1+N));

        if(Wremain>0){
            result+=(H/(1+N));
            
            if(Hremain>0){
                result+=1;
            }
        }

        if(Hremain>0){
            result+=(W/(1+M));
        }

        System.out.println(result);
    }
}

package Java2024.October;

import java.util.Scanner;

public class ZOAC4 {
    public static void main(String[] args) throws Exception{
        // String path=System.getProperty("user.dir")+"/2024/10월/";
        Scanner sc=new Scanner(System.in);

        double H=sc.nextInt();
        double W=sc.nextInt();
        double N=sc.nextInt();
        double M=sc.nextInt();

        int result=(int)(Math.ceil(W/(1+M))*Math.ceil(H/(1+N)));

        System.out.println(result);
    }
}

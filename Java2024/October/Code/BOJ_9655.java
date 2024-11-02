package Code;

import java.io.*;
import java.util.*;

public class BOJ_9655 {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);

        int N=sc.nextInt();

        if(N%2==0){
            System.out.println("CY");
        }else{
            System.out.println("SK");
        }
    }
}

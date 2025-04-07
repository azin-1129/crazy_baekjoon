package Code;

import java.util.*;
import java.io.*;

class BOJ_25206{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=25206;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int count=0;
        double multiplyResult=0;
        double creditTotal=0;
        for(int i=0;i<20;i++){
            String[] input=br.readLine().split(" ");

            if(input[2].equals("P")){
                continue;
            }

            count++;
            double credit=Double.parseDouble(input[1]);

            double rating=0;
            switch(input[2]){
                case "A+":
                    rating=4.5;
                    break;
                case "A0":
                    rating=4.0;
                    break;
                case "B+":
                    rating=3.5;
                    break;
                case "B0":
                    rating=3.0;
                    break;
                case "C+":
                    rating=2.5;
                    break;
                case "C0":
                    rating=2.0;
                    break;
                case "D+":
                    rating=1.5;
                    break;
                case "D0":
                    rating=1.0;
                    break;
            }
            multiplyResult+=credit*rating;
            creditTotal+=credit;
        }
        System.out.printf("%.6f", multiplyResult/creditTotal);
        br.close();
    }
}
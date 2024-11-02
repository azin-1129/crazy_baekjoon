package Code;

import java.io.*;
import java.util.*;

public class BOJ_5073 {
    public static void main(String[] args) throws Exception{
        String path=System.getProperty("user.dir")+"\\Java2024\\October\\";
        BufferedReader br=new BufferedReader(new FileReader(path+"input5073.txt"));

        while(true){
            int[] numbers=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(numbers);

            int x=numbers[0];
            int y=numbers[1];
            int z=numbers[2];
            
            if((x+y+z)==0){
                break;
            }

            if((x+y)<=z){
                System.out.println("Invalid");
                continue;
            }

            if(x==y){
                if(x==z){
                    System.out.println("Equilateral");
                }else{
                    System.out.println("Isosceles");
                }
            }else if(y==z){
                if(x==z){
                    System.out.println("Equilateral");
                }else{
                    System.out.println("Isosceles");
                }
            }else if(x==z){
                if(y==z){
                    System.out.println("Equilateral");
                }else{
                    System.out.println("Isosceles");
                }
            }else{
                System.out.println("Scalene");
            }
        }
    }
}

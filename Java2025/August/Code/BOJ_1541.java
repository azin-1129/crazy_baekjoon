package Code;

import java.util.*;
import java.io.*;

class BOJ_1541{
    static Queue<String> q=new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1541;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        String[] input=br.readLine().split("");
        for(int i=0;i<input.length;i++){
            if(input[i].equals("-")){
                for(int j=(i+1);j<input.length;j++){
                    if(input[j].equals("-")){
                        break;
                    }else if(input[j].equals("+")){
                        input[j]="-";
                    }
                }
            }
        }
        System.out.println(Arrays.toString(input));

        for(int i=0;i<input.length;i++){
            q.offer(input[i]);
        }
        int result=parseNumber();

        while(!q.isEmpty()){
            String c=q.peek();
            switch(c){
                case "+":
                    q.poll();
                    result+=parseNumber();
                    break;
                case "-":
                    q.poll();
                    result-=parseNumber();
                    break;
                
            }
        }

        System.out.println(result);
        br.close();
    }
    static int parseNumber(){
        String temp="";

        while(!q.isEmpty()){
            String c=q.peek();
            if(c.equals("-") || c.equals("+")){
                break;
            }
            temp+=q.poll();
        }
        return Integer.valueOf(temp);
    }
}
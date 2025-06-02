package Code;

import java.util.*;
import java.io.*;

class BOJ_문제번호{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=3986;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int result=0;
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            Stack<String> stack=new Stack<>();
            String[] input=br.readLine().split("");
            // System.out.println(Arrays.toString(input));

            for(String s:input){
                if(stack.isEmpty()){
                    stack.push(s);
                    continue;
                }else{
                    if(stack.peek().equals(s)){
                        stack.pop();
                    }else{
                        stack.push(s);
                    }
                }
            }

            if(stack.isEmpty()){
                result+=1;
            }
        }
        System.out.println(result);
        br.close();
    }
}
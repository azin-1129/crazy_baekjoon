package Code;

import java.util.*;
import java.io.*;

class BOJ_1874{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1874;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        int N=Integer.parseInt(br.readLine());
        
        StringBuilder sb=new StringBuilder();
        Stack<Integer> stack=new Stack<>();
        boolean flag=false;
        int currentValue=0;
        for(int i=0;i<N;i++){
            int number=Integer.parseInt(br.readLine());

            if(currentValue<number){
                for(int val=currentValue+1;val<=number;val++){
                    stack.add(val);
                    sb.append("+\n");
                }
                currentValue=number;
            }else{
                if(stack.peek()!=number){
                    flag=true;
                    break;
                }
            }
            stack.pop();
            sb.append("-\n");
        }
        if(flag){
            System.out.println("NO");
        }else{
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
        }
        br.close();
    }
}
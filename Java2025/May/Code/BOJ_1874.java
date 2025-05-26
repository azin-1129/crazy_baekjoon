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
        // int firstValue=Integer.parseInt(br.readLine());
        // for(int i=1;i<=firstValue;i++){
        //     stack.add(i);
        //     sb.append("+\n");
        // }
        // stack.pop();
        // sb.append("-\n");
        boolean flag=false;
        int currentValue=0;
        for(int i=0;i<N;i++){
            int number=Integer.parseInt(br.readLine());

            if(stack.isEmpty()){
                for(int val=currentValue+1;val<=number;val++){
                    stack.add(val);
                    sb.append("+\n");
                }
                currentValue=number;
            }
            int peekValue=stack.peek();
            if(peekValue<number){
                for(int val=currentValue+1;val<=number;val++){
                    stack.add(val);
                    sb.append("+\n");
                }
                stack.pop();
                currentValue=number;
                sb.append("-\n");
            }else{
                if(peekValue==number){
                    stack.pop();
                    sb.append("-\n");
                }else{
                    flag=true;
                    break;
                }
            }
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
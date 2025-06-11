package Code;

import java.util.*;
import java.io.*;

class BOJ_2504{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2504;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        Stack<String> operator=new Stack<>();
        int result=0;

        String[] inputs=br.readLine().split("");
        boolean flag=false;
        int temp=1;
        for(int i=0;i<inputs.length;i++){
            String current=inputs[i];

            if(current.equals("[")){
                operator.push(current);
                temp*=3;
            }

            if(current.equals("(")){
                operator.push(current);
                temp*=2;
            }

            if(current.equals("]")){
                if(operator.isEmpty() || !operator.peek().equals("[")){
                    flag=true;
                    break;
                }

                if(inputs[i-1].equals("[")){ // 즉시 닫혔음. 현재 괄호쌍을 단일 값으로 처리
                    result+=temp;
                }
                operator.pop();
                temp/=3;
            }

            if(current.equals(")")){
                if(operator.isEmpty() || !operator.peek().equals("(")){
                    flag=true;
                    break;
                }

                if(inputs[i-1].equals("(")){ // 즉시 닫혔음. 현재 괄호쌍을 단일 값으로 처리
                    result+=temp;
                }
                operator.pop();
                temp/=2;
            }
        }

        if(flag || !operator.isEmpty()){
            System.out.println(0);
        }else{
            System.out.println(result);
        }
        br.close();
    }
}
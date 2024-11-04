package Code;

import java.util.*;
import java.io.*;

class BOJ_1918{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=1918;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        StringBuilder sb=new StringBuilder();
        Stack<Character> stack=new Stack<>();

        String[] inputs=br.readLine().split("");

        for(int i=0;i<inputs.length;i++){
            char current=inputs[i].charAt(0);

            switch(current){
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && priority(stack.peek())>=priority(current)){ // 우선순위 같으면 순차적으로
                            sb.append(stack.pop());
                    }
                    stack.push(current);
                    break;
                case '(':
                    stack.push(current);
                    break;
                case ')':
                    while(!stack.isEmpty() & stack.peek()!='('){
                        sb.append(stack.pop());
                    }
                    stack.pop(); // '(' 제거
                    break;
                default:
                    sb.append(current);
                    break;
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);

        br.close();
    }
    static int priority(char op){
        if(op=='*' || op=='/'){
            return 3;
        }else if(op=='+' || op=='-'){
            return 2;
        }

        return 1;
    }
}
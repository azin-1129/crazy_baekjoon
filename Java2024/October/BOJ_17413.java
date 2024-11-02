import java.util.*;
import java.io.*;

class BOJ_17413{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        int bojNum=17413;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        StringBuilder sb=new StringBuilder();

        Stack<Character> stack1=new Stack<>();
        Stack<Character> stack2=new Stack<>(); // to reverse

        String s=br.readLine();

        for(int i=0;i<s.length();i++){
            char current=s.charAt(i);

            if(current=='<'){
                while(!stack1.isEmpty()){
                    sb.append(stack1.pop());
                }
                sb.append(current);
            }else if(current=='>'){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                while(!stack2.isEmpty()){
                    sb.append(stack2.pop());
                }
                sb.append(current);
            }else if(current==' '){
                while(!stack1.isEmpty()){
                    sb.append(stack1.pop());
                }
                sb.append(current);
            }else{
                stack1.push(current);

                if(i==s.length()-1){
                    while(!stack1.isEmpty()){
                        sb.append(stack1.pop());
                    }
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}
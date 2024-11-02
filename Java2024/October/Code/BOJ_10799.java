package Code;
import java.util.*;
import java.io.*;

class BOJ_10799{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\";
        int bojNum=10799;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));
        int result=0;
        
        String status=br.readLine();

        status=status.replace("()","x");

        Stack<Character> stack=new Stack<>();

        for(int i=0;i<status.length();i++){
            char stat=status.charAt(i);

            if(stat=='x'){
                result+=stack.size();
            }else if(stat==')'){
                stack.pop();
                result+=1;
            }else{
                stack.push(stat);
            }
        }

        System.out.println(result);

        br.close();
    }
}
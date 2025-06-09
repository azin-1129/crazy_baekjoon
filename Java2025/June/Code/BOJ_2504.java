package Code;

import java.util.*;
import java.io.*;

class BOJ_2504{
    public static void main(String[] args) throws Exception{
        String filepath=System.getProperty("user.dir")+"\\Input\\";
        int bojNum=2504;
        BufferedReader br=new BufferedReader(new FileReader(filepath+"input"+bojNum+".txt"));

        Stack<Integer> operand=new Stack<>();
        Stack<String> operator=new Stack<>();
        Stack<Integer> result=new Stack<>();
        int calcResult=0;

        int level=0;
        int closedLevel=0;
        String[] inputs=br.readLine().split("");
        boolean flag=false;
        for(int i=0;i<inputs.length;i++){
            System.out.println("operand:"+operand);
            System.out.println("operator:"+operator);
            System.out.println("result:"+result);
            System.out.println(calcResult);
            System.out.println();
            String current=inputs[i];

            if(current.equals("[")){
                operator.push(current);
                level+=1;
                continue;
            }

            if(current.equals("(")){
                operator.push(current);
                level+=1;
                continue;
            }

            if(current.equals("]")){
                if(!operator.peek().equals("[")){
                    flag=true;
                    break;
                }

                operator.pop();
                if((level-1)==closedLevel){
                    operand.push(3);
                }else{
                    int temp=0;
                    if(!operand.isEmpty()){
                        while(!operand.isEmpty()){
                            temp+=operand.pop();
                        }
                        temp*=3;   
                    }else{
                        temp=3;
                    }
                    result.push(temp);

                    if((level-1)==0){ // result 합산
                        while(!result.isEmpty()){
                            calcResult+=result.pop();
                        }
                        calcResult+=temp;
                    }
                }
                closedLevel=level;
                level-=1;
            }

            if(current.equals(")")){
                if(!operator.peek().equals("(")){
                    flag=true;
                    break;
                }

                operator.pop();
                if((level-1)==closedLevel){
                    operand.push(2);
                }else{ // 합산
                    int temp=0;
                    if(!operand.isEmpty()){
                        while(!operand.isEmpty()){
                            temp+=operand.pop();
                        }
                        temp*=2;
                        result.push(temp); // 추후 합산할 결괏값 스택에 저장
                    }else{
                        temp=2;
                    }

                    if((level-1)==0){ // 괄호식이 끝났기 때문에 정산
                        while(!result.isEmpty()){
                            calcResult+=result.pop();
                        }
                    }
                }
                closedLevel=level;
                level-=1;
            }
        }

        if(flag){
            System.out.println(0);
        }else{
            System.out.println(calcResult);
        }
        br.close();
    }
}
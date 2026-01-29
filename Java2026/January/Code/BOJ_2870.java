package Code;

import java.util.*;
import java.io.*;

class BOJ_2870 {
    public static void main(String[] args) throws Exception {
        String filepath = System.getProperty("user.dir") + "\\Input\\";
        int bojNum=2870;
        BufferedReader br = new BufferedReader(new FileReader(filepath + "input" + bojNum + ".txt"));

        // isDigit을 사용해 숫자 열 뽑아내서, 앞의 0 소거하고 오름차순 출력
        // 혹시 모르니, 입력받을 문자열 앞 뒤에 임의 문자를 추가한다.
        // 그런 다음, 현재 i번째 문자가 유효 숫자가 아닐 경우 StringBuilder에 추가

        // Long 타입으로 비교 불가
        // length부터 비교.
        // length가 같다면.. 각각 숫자에 대해 크기 비교 시작
        PriorityQueue<String> pq=new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String x, String y){
                int xLength=x.length();
                int yLength=y.length();
                
                if(xLength<yLength){
                    return -1;
                }else if(xLength==yLength){
                    for(int i=0;i<xLength;i++){
                        int xVal=Character.valueOf(x.charAt(i));
                        int yVal=Character.valueOf(y.charAt(i));
                        if(xVal<yVal){
                            return -1;
                        }else if(xVal>yVal){
                            return 1;
                        }
                    }
                    return 0;
                }else{
                    return 1;
                }
            }
        });
        StringBuilder sb=new StringBuilder();
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String str=br.readLine();
            str="@".concat(str).concat("@");
            String temp="";
            for(int j=0;j<str.length();j++){
                if(Character.isDigit(str.charAt(j))){
                    temp+=str.charAt(j);
                }else{
                    if(!temp.isEmpty()){
                        temp=temp.replaceAll("^0+","");
                        if(temp.equals("")){
                            temp="0";
                        }
                        pq.offer(temp);
                        temp="";
                    }
                }
            }
        }

        while(!pq.isEmpty()){
            sb.append(pq.poll()+"\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
        br.close();
   }
}
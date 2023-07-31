import java.io.*;
import java.util.*;

public class Solution4949 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		List<String> s=Arrays.asList("(",")","[","]"); // 괄호 리스트
		List<String> lines=new ArrayList<String>(); // 입력 받는 문자열
		List<String> res=new ArrayList<>(); // 결과
		
//		for(int t=0;t<8;t++) {
//			lines[t]=br.readLine();
//		}
//		
		while((lines.add(br.readLine()))) {
			for(String line:lines) {
				Stack<String> stack=new Stack<>(); // 괄호 비교를 위한 스택
				
				System.out.println("line:"+line);
				for(String c:line.split("")) {
					if(c.equals(" ")) continue;
					//System.out.println("c:"+c);
					if(!s.contains(c)) {
						if(c.equals(".")) {
							if(!stack.empty()) {
								res.add("no");
								break;
							}
						}
					}
					
					//System.out.println("stack:"+stack);
					if(c.equals("(") || c.equals("[")) {
						stack.add(c);
					} else if(c.equals(")") || c.equals("]")){
						if(stack.empty()) {res.add("no"); break;}
						String temp=stack.peek();
						if(temp.equals("(")) {
							if(c.equals(")")){
								stack.pop();
								continue;
							} else {
								res.add("no");
								break;
							}
						}
						else if(temp.equals("[")) {
							if(c.equals("]")){
								stack.pop();
								continue;
							} else {
								res.add("no");
								break;
							}
						}
					}
					
					if(stack.empty() && c.equals(".")) {
						res.add("yes");
						//System.out.println("line:"+line+"res: yes");
					}
				}
			}
		
			for(String r:res) {
				System.out.println(r);
			}

		}
	}

}

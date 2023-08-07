package silver;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.script.*;

public class Solution1935 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("C:\\SSAFY\\workspaces\\02-Java\\BaekJoon\\src\\silver\\input1935.txt"));
		//BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N=Integer.parseInt(br.readLine());
		String[] stklne=br.readLine().split("");
		Stack<String> stack=new Stack<>();
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("JavaScript");
		List<String> chk_lst= Arrays.asList("+","-","/","*");
		int[] nums=new int[N];
		
		for(int n=0;n<N;n++) {
			nums[n]=Integer.parseInt(br.readLine());
		}
		
		//System.out.println(Integer.valueOf("A")); String 자체가 valueOf가 안됨
		//System.out.println(Integer.valueOf("A".charAt(0)));
		
		for(String stk:stklne) {
			System.out.println("stack:"+stack);
			if (chk_lst.contains(stk)){
				//System.out.println("chk_listed");
				String y=stack.pop();
				String x=stack.pop();
				
				if(Pattern.matches("^[a-zA-Z]*$", y)){ // 알파벳 감별
					y=String.valueOf(Integer.valueOf(y.charAt(0))-64); // 숫자 값으로 변환
				}
				if(Pattern.matches("^[a-zA-Z]*$", x)){ // 알파벳 감별
					x=String.valueOf(Integer.valueOf(x.charAt(0))-64); // 숫자 값으로 변환
				}
				
				System.out.println("x:"+x+" y:"+y);
				//System.out.println(Integer.valueOf(x.charAt(0)-65)+stk+Integer.valueOf(y.charAt(0)-65));
				stack.add(String.valueOf(engine.eval(x+stk+y)));
			}else {
				stack.add(stk);
			}
		}
		
		System.out.printf("%.2f",Double.parseDouble(stack.pop()));
		br.close();

	}

}

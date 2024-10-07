public import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.util.Arrays;

import java.util.StringTokenizer;



public class Solution벌꿀채취 {



	public static void main(String[] args) throws Exception{

		// TODO Auto-generated method stub

		

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		

		int[][] map;

		

		int T=Integer.parseInt(br.readLine());

	

		for(int t=1;t<=T;t++) {

			int Ctmp=0; int totalHoney=0; int HoneyTmp=0;

			int ax=0; int ay1=0; int ay2=0; int bee1=0;

			int bee2Max=0;



			st=new StringTokenizer(br.readLine());

			

			int N=Integer.parseInt(st.nextToken());

			int M=Integer.parseInt(st.nextToken());

			int C=Integer.parseInt(st.nextToken());

			

			map=new int[N][N];

			

			for(int n=0;n<N;n++) {

				map[n]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			}

			

			for(int x=0;x<(N-M+1);x++) {

				for(int y=0;y<(N-M+1);y++) { // 경우의 수 생성

					Ctmp=0; totalHoney=0; bee1=0;

					for(int z=0;z<M;z++) {

						if(Ctmp+map[x][y+z]>C) continue;

						

						bee1+=(map[x][y+z]*map[x][y+z]);

						// 일꾼 1의 꿀 총합. (경우의 수)

					}

					

					ax=x;

					ay1=y;

					ay2=ay1+M; // 일꾼 1의 좌표들

					

					System.out.println("X:"+x+", y:"+y+", ax:"+ax);

					

					System.out.println("bee1의 x:"+ax+", ay1~ay2:"+ay1+","+ay2);

					System.out.println("bee1의 합:"+bee1);

					

					// 동일행 좌 처리

					System.out.println("동일행좌처리");

					System.out.println("ㅡㅡㅡㅡㅡbee2 process");

					if(ay1>M) {

						for(int i=0;(i+M)<ay1;i++) {

							Ctmp=0; HoneyTmp=0;

							for(int j=0;j<M;j++) {

								if(Ctmp+map[ax][i+j]>C) continue;

								

								HoneyTmp+=(map[ax][i+j]*map[ax][i+j]);

								System.out.println(ax+","+(i+j)+"합산중.");

							}

							System.out.println("ㅡㅡㅡㅡㅡㅡㅡ");

							// 일꾼 2의 꿀 총합 완성

							bee2Max=Math.max(HoneyTmp, bee2Max); // 최대값 갱신

						}

					}

					if(ay1==M) {

						Ctmp=0; HoneyTmp=0;

						

						for(int i=0;i<M;i++) {

							if(Ctmp+map[ax][i]>C) continue;

							

							HoneyTmp+=map[ax][i];

							System.out.println(ax+","+i+"합산중.");

						}

						// 일꾼 2 꿀 총합 완성

						System.out.println("ㅡㅡㅡㅡㅡㅡㅡ");

						bee2Max=Math.max(HoneyTmp, bee2Max);

					}

					

					// 동일행 우 처리

					System.out.println("동일행 우처리");

					if((N-(ay2-1))>M) {

						for(int i=(ay2);(i+M)<N;i++) {

							Ctmp=0; HoneyTmp=0;

							for(int j=0;j<(M-1);j++) {

								if(Ctmp+map[ax][i+j]>C) continue;

								Ctmp+=map[ax][i+j];

								

								HoneyTmp+=(map[ax][i+j]*map[ax][i+j]);

								System.out.println(ax+","+(i+j)+"합산중.");

							}

							System.out.println("bee2 temp:"+HoneyTmp);

							System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

							// 일꾼 2의 꿀 총합 완성

							bee2Max=Math.max(HoneyTmp, bee2Max); // 최대값 갱신

						}

					}

					if((N-(ay2))==M) {

						Ctmp=0; HoneyTmp=0;

						

						for(int i=(ay2-1);(i+(M-1))<N;i++) {

							System.out.println("i:"+i);

							if(Ctmp+map[ax][i]>C) continue;

							Ctmp+=map[ax][i];

							

							HoneyTmp+=map[ax][i];

							System.out.println(ax+","+i+"합산중.");

						}

						// 일꾼 2 꿀 총합 완성

						System.out.println("bee2 temp:"+HoneyTmp);

						System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

						bee2Max=Math.max(HoneyTmp, bee2Max);

					}

					

					System.out.println("동일행 제외 처리");

					

					for(int x2=0;x2<(N-M)+1;x2++) {

						if(x2==ax) continue; // 동일행 처리는 이미 했다.

						for(int y2=0;y2<(N-M)+1;y2++) {

							Ctmp=0; HoneyTmp=0;

							for(int m=0;m<M;m++) {

								if(Ctmp+map[x2][y2+m]>C) continue;

								

								Ctmp+=map[x2][y2+m];

								

								HoneyTmp+=(map[x2][y2+m]*map[x2][y2+m]);

								System.out.println(x2+","+(y2+m)+"합산중.");

							}

							System.out.println("bee2 temp:"+HoneyTmp);

							System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

							// 일꾼 2 꿀 총합 완성

							bee2Max=Math.max(HoneyTmp, bee2Max); // 최대값 갱신

						}

					}

					System.out.println("bee2의 최대합:"+bee2Max);

					// 일꾼 1의 경우에서 겹치지 않는 제일 큰 일꾼 2 꿀 총합 완성

					totalHoney=Integer.max(totalHoney,(bee1+bee2Max));

				}

			}

			bw.write(totalHoney+"\n");

	

		}

		// totalHoney=채취한 꿀 합의 쵀댓값.

		// bee2Max=일꾼2가 연속적으로 채취 시 제일 큰 경우의 값

		// bee1=경우의 수 당시 일꾼 1이 채집한 꿀의 값.

		// N=벌통 나열된 맵의 가로, 세로.

		// M=일꾼이 연속적으로 채취할 수 있는 벌통의 수

		// C=일꾼이 한 경우의 수 당 채굴할 수 있는 벌통의 최대 크기.

		// Ctmp=경우마다 C를 초과하는지 능동적 체크를 위한 변수

		// map=벌통맵.

		// HoneyTmp=bee2Max 갱신을 위한 값.

		

		bw.flush();

		bw.close();

		br.close();

	}



} {
    
}

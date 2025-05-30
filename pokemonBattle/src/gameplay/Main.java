package gameplay;

import pokemon.*;
import skill.*;
import java.util.function.Consumer;


import java.util.*;

public class Main {
	public static Consumer<String> logCallback = System.out::println;
	public static Scanner sc = new Scanner(System.in);
	public static GameManager gm = new GameManager();
	public static Random rand = new Random();
	public static Player player = new Player();
	
	 public static void waveCount() {
     	int wave = 1;
     	while(wave<11) {
     	logCallback.accept("");
        logCallback.accept("========= "+wave + "Wave"+" =========");
         while(true) {
         	Pokemon enemy;
	       		 logCallback.accept("무슨 행동을 할까?");
	       		 logCallback.accept("1. 앞으로 나아간다 / 2. 포켓몬 상태 확인 / 3. 포켓몬 치료(남은 횟수:"+(3-gm.healcnt)+"회)");
         	int choice = sc.nextInt();
         		logCallback.accept("");
	            if(choice==1) {
	            
	            	logCallback.accept("앞으로 나아갑니다.");
	            	if(wave==5) {
	            		enemy = gm.createWild(6); //페르시온 보스전
	            		logCallback.accept("날카로운 고양이 울음소리 같은 것이 들립니다...");
	            	}
	            	else if(wave==10) {
	            		enemy = gm.createWild(7); //레쿠쟈 보스전
	            		logCallback.accept("여정의 끝이 보이는 것 같습니다...");
	                	logCallback.accept("공기가 스산합니다.");
	            	}
	            	else {
	            		int num = new Random().nextInt(5) + 1; // 1~5 중 랜덤 (보스 제외)
	            		enemy = gm.createWild(num);
	            	
	            	logCallback.accept("야생의 " + enemy.name + "이(가) 나타났다!");
	            	logCallback.accept("");
	            	
	            	gm.battle(player.myPkm, enemy);
	            	if(player.myPkm.spdCnt>0||player.myPkm.atkCnt>0) {
	            		player.myPkm.spd -= player.myPkm.spdCnt*10;
	            		player.myPkm.atk -= player.myPkm.atkCnt*10; 
	            		player.myPkm.spdCnt = 0;
	            		player.myPkm.atkCnt = 0;
	            	}
	            	
	            	break;
	            }
	            }   

	            
	            else if(choice==2) {
	            	logCallback.accept("=====================================");
	            	logCallback.accept(String.format("%s의 현재 체력은 %.2f 입니다.\n", player.myPkm.name, player.myPkm.hp));
	            	logCallback.accept(player.myPkm.name+"의 현재 공격력은 " + player.myPkm.atk + "입니다.");
	            	logCallback.accept(player.myPkm.name+"의 현재 방어력은 " + player.myPkm.def + "입니다.");
	            	logCallback.accept(player.myPkm.name+"의 현재 스피드는 " + player.myPkm.spd + "입니다.");
	            	logCallback.accept("=====================================");
	            	logCallback.accept("");
	            }
	            else if(choice==3) {
	            	logCallback.accept("=====================================");
	            	gm.heal(player.myPkm);
	            	logCallback.accept("=====================================");
	            	logCallback.accept("");
	            }

            }
         	wave++;
            	
        }

     }
         
    public static void main(String[] args) {
        
        
        Main.logCallback.accept("포켓몬 배틀 start!!!");
        System.out.print("당신의 이름은 무엇인가요?: ");
        
        player.name = sc.nextLine();
        
        Main.logCallback.accept("???: 신참 트레이너인가...");
        Main.logCallback.accept("???: "+player.name+"! 포켓몬의 세계에 잘 왔다. \n오박사: 나는 오박사. 포켓몬을 연구하고 있지.\n오박사: 너의 첫 포켓몬을 골라보렴...");
        Main.logCallback.accept("오박사가 몬스터볼 세 개를 내밀었습니다.");
        Main.logCallback.accept("몬스터볼 선택: 1.이상해씨 2.파이리 3.꼬부기");
        System.out.print("당신의 선택은 : ");
        int select = sc.nextInt();
        player.myPkm = gm.selectStarting(select);
        Main.logCallback.accept("오박사: "+player.myPkm.name+"구나. 좋은 포켓몬이지!");
        Main.logCallback.accept("오박사: 전설의 포켓몬 레쿠쟈가 폭주하고 있어... 네가 막아주었으면 좋겠구나.");
        Main.logCallback.accept("오박사: 앞으로의 여정이 찬란하기를!");
        
        waveCount();
        

        Main.logCallback.accept("=======================");
        Main.logCallback.accept("레쿠쟈: 키에에에에에엑!!!");
        Main.logCallback.accept("레쿠쟈가 몸부림칩니다.\n갑자기, 레쿠쟈의 몸이 빛나기 시작했습니다.");
        Main.logCallback.accept("당신은 레쿠쟈를 지켜봅니다. 그리고, 이내 형상이 변하기 시작합니다.");
        Main.logCallback.accept("???: 에에---- 메---");
        Main.logCallback.accept("메타몽: 메...");
        Main.logCallback.accept("폭주한 레쿠쟈의 정체는 사실 메타몽이었습니다!!!");
        Main.logCallback.accept("오박사: "+player.name+"!!! 괜찮나?!");
        Main.logCallback.accept("메타몽: 메타...");
        Main.logCallback.accept("오박사: 폭주한 레쿠쟈의 정체가 사실은 변신한 메타몽이었다고?! 정말 놀랄 노자로군.");
        Main.logCallback.accept("오박사: 수고 많았네. "+player.name+", "+player.myPkm.name+".");
        Main.logCallback.accept("오박사: 어엿한 포켓몬 트레이너가 되었어... 앞으로도 여정을 계속할 텐가?");
        Main.logCallback.accept("1: 예, 2: 아니오");
        int answer = sc.nextInt();
        if(answer==1) {
        Main.logCallback.accept("오박사: 그래... 앞으로도 펼쳐질 여정이 찬란하기를!");
        Main.logCallback.accept(player.myPkm.name+": 캬아!");
        }
        else if(answer==2) {
            Main.logCallback.accept(player.myPkm.name+": 캬아!");
            Main.logCallback.accept("오박사: 허허. "+player.myPkm.name+"는 생각이 다른 것 같은데?");
            Main.logCallback.accept("오박사: 어찌 되었건... 앞으로도 펼쳐질 여정이 찬란하기를!");
            }
        Main.logCallback.accept(player.name+"(와)과 "+player.myPkm.name+"는 또다른 여행을 떠났습니다!!");
        Main.logCallback.accept("\n\n=======================");
        Main.logCallback.accept("========The End========");
        Main.logCallback.accept("=======================");
        Main.logCallback.accept("");
    }

    
    

}

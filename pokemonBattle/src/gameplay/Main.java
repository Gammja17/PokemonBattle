package gameplay;

import pokemon.*;
import skill.*;


import java.util.*;

public class Main {
	
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        GameManager gm = new GameManager();
        
        System.out.println("게임을 시작합니다");
        System.out.println("당신의 이름은 무엇인가요?: ");
        
        Player player = new Player();
        player.name = sc.nextLine();
        
        System.out.println("???: 신참 트레이너인가...");
        System.out.println("???: "+player.name+"! 포켓몬의 세계에 잘 왔다. \n오박사: 나는 오박사. 포켓몬을 연구하고 있지.\n오박사: 너의 첫 포켓몬을 골라보렴...");
        System.out.println("오박사가 몬스터볼 세 개를 내밀었습니다.");
        System.out.println("몬스터볼 선택: 1.이상해씨 2.파이리 3.꼬부기");
        
        int select = sc.nextInt();
        player.myPkm = gm.selectStarting(select);
        System.out.println("오박사: "+player.myPkm.name+"구나. 좋은 포켓몬이지!");
        System.out.println("오박사: 전설의 포켓몬 레쿠쟈가 폭주하고 있어... 네가 막아주었으면 좋겠구나.");
        System.out.println("오박사: 앞으로의 여정이 찬란하기를!");
        
        int wave = 1;
        
        while(wave<=10) {
        	switch(wave) {
            case 1: case 2: case 3: case 4: case 6: case 7: case 8: case 9:
            System.out.println("");
            System.out.println("========= "+wave + "Wave"+" =========");
            while(true) {
	       		 System.out.println("무슨 행동을 할까?");
	       		 System.out.println("1. 앞으로 나아간다 / 2. 포켓몬 상태 확인");
            	int choice = sc.nextInt();
	            if(choice==1) {
	            	int num = new Random().nextInt(5) + 1; // 1~5 중 랜덤 (보스 제외)
	            	Pokemon enemy = gm.createWild(num);
	            	System.out.println("앞으로 나아갑니다.");
	            	System.out.println("야생의 " + enemy.name + "이(가) 나타났다!");
	            	
	            	gm.battle(player.myPkm, enemy);
	            	if(player.myPkm.spdCnt>0||player.myPkm.atkCnt>0) {
	            		player.myPkm.spd -= player.myPkm.spdCnt*10;
	            		player.myPkm.atk -= player.myPkm.atkCnt*10; 
	            		player.myPkm.spdCnt = 0;
	            		player.myPkm.atkCnt = 0;
	            	}
	            	
	            	break;
	            }
	            else if(choice==2) {
	            	System.out.println(player.myPkm.name+"의 현재 체력은 "+player.myPkm.hp+"입니다.");
	            }
            }
            break;
            
            case 5:
                System.out.println(" ");
            	System.out.println("========= "+wave + "Wave"+" =========");
            	System.out.println("날카로운 고양이 울음소리 같은 것이 들립니다...");
            	while(true) {
            		 System.out.println("무슨 행동을 할까?");
            		 System.out.println("1. 앞으로 나아간다 / 2. 포켓몬 상태 확인");
                	int choice = sc.nextInt();
    	            if(choice==1) {
    	            	Pokemon enemy = gm.createWild(6);
    	            	System.out.println("앞으로 나아갑니다.");
    	            	System.out.println("야생의 " + enemy.name + "이(가) 나타났다!");
    	            	
    	            	gm.battle(player.myPkm, enemy);
    	            	if(player.myPkm.spdCnt>0||player.myPkm.atkCnt>0) {
    	            		player.myPkm.spd -= player.myPkm.spdCnt*10;
    	            		player.myPkm.atk -= player.myPkm.atkCnt*10; 
    	            		player.myPkm.spdCnt = 0;
    	            		player.myPkm.atkCnt = 0;
    	            		
    	            	}
    	            	break;
    	            }
    	            else if(choice==2) {
    	            	System.out.println(player.myPkm.name+"의 현재 체력은 "+player.myPkm.hp+"입니다.");
    	            }
                }
            	break;
            	
            case 10:
                System.out.println(" ");
            	System.out.println("========= "+wave + "Wave"+" =========");
            	System.out.println("여정의 끝이 보이는 것 같습니다...");
            	System.out.println("공기가 스산합니다.");
            	while(true) {
           		 System.out.println("무슨 행동을 할까?");
           		 System.out.println("1. 앞으로 나아간다 / 2. 포켓몬 상태 확인");
            		int choice = sc.nextInt();
    	            if(choice==1) {
    	            	Pokemon enemy = gm.createWild(7);
    	            	System.out.println("앞으로 나아갑니다.");
    	            	System.out.println("야생의 " + enemy.name + "이(가) 나타났다!");
    	            	
    	            	gm.battle(player.myPkm, enemy);
    	            	if(player.myPkm.spdCnt>0||player.myPkm.atkCnt>0) {
    	            		player.myPkm.spd -= player.myPkm.spdCnt*10;
    	            		player.myPkm.atk -= player.myPkm.atkCnt*10; 
    	            		player.myPkm.spdCnt = 0;
    	            		player.myPkm.atkCnt = 0;
    	            	}
    	            	break;
    	            }
    	            else if(choice==2) {
    	            	System.out.printf(player.myPkm.name+"의 현재 체력은 %.2f 입니다.", player.myPkm.hp);
    	            }
                }
            	break;
            }
        	wave++;
        	
        }  
        System.out.println("=======================");
        System.out.println("레쿠쟈: 키에에에에에엑!!!");
        System.out.println("레쿠쟈가 몸부림칩니다.\n갑자기, 레쿠쟈의 몸이 빛나기 시작했습니다.");
        System.out.println("당신은 레쿠쟈를 지켜봅니다. 그리고, 이내 형상이 변하기 시작합니다.");
        System.out.println("???: 에에---- 메---");
        System.out.println("메타몽: 메...");
        System.out.println("폭주한 레쿠쟈의 정체는 사실 메타몽이었습니다!!!");
        System.out.println("오박사: "+player.name+"!!! 괜찮나?!");
        System.out.println("메타몽: 메타...");
        System.out.println("오박사: 폭주한 레쿠쟈의 정체가 사실은 변신한 메타몽이었다고?! 정말 놀랄 노자로군.");
        System.out.println("오박사: 수고 많았네. "+player.name+", "+player.myPkm.name+".");
        System.out.println("오박사: 어엿한 포켓몬 트레이너가 되었어... 앞으로도 여정을 계속할 텐가?");
        System.out.println("1: 예, 2: 아니오");
        int answer = sc.nextInt();
        if(answer==1) {
        System.out.println("오박사: 그래... 앞으로도 펼쳐질 여정이 찬란하기를!");
        System.out.println(player.myPkm.name+": 캬아!");
        }
        else if(answer==2) {
            System.out.println(player.myPkm.name+": 캬아!");
            System.out.println("오박사: 허허. "+player.myPkm.name+"는 생각이 다른 것 같은데?");
            System.out.println("오박사: 어찌 되었건... 앞으로도 펼쳐질 여정이 찬란하기를!");
            }
        System.out.println(player.name+"(와)과 "+player.myPkm.name+"는 또다른 여행을 떠났습니다!!");
        System.out.println("\n\n=======================");
        System.out.println("========The End========");
        System.out.println("=======================");
        System.out.println("");
    }
    
    
    

}

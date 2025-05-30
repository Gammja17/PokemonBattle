package gameplay;

import pokemon.Pokemon;
import pokemon.Starting;
import skill.Skill;
import skill.SpecialSkill;

import java.util.*;

public class GameManager {
	
	public int healcnt = 0;
	
	Pokemon[] wildPool = new Pokemon[5];
	
	Skill 몸통박치기 = new Skill("몸통박치기", Type.노말, 30, 95);
	Skill 할퀴기 = new Skill("할퀴기", Type.노말, 20, 100);
	Skill 덩굴채찍 = new Skill("덩굴채찍", Type.풀, 40, 100);
	Skill 불꽃세례 = new Skill("불꽃세례", Type.불, 40, 100);
	Skill 물대포 = new Skill("물대포", Type.물, 40, 100);
	Skill 솔라빔 = new Skill("솔라빔", Type.풀, 100, 85);
	Skill 화염방사 = new Skill("화염방사", Type.불, 90, 100);
	Skill 하이드로펌프 = new Skill("하이드로펌프", Type.물, 120, 70);
	Skill 마구할퀴기 = new Skill("마구할퀴기", Type.노말, 60, 100);
	Skill 용성군 = new Skill("용성군", Type.노말, 130, 90);
	
	Skill 고속이동 = new SpecialSkill("고속이동", Type.노말, 0, 100); 
	Skill 칼춤 = new SpecialSkill("칼춤", Type.노말, 0, 100);       
	Skill 방어 = new SpecialSkill("방어", Type.노말, 0, 100); 
	
	
	
	public Starting selectStarting(int select) {
		Starting 디폴트 = new Starting("이상해씨", 160, 20, 40, 20, Type.풀,1);
		switch(select) {
		case 1 : //이상해씨
			Starting 이상해씨 = new Starting("이상해씨", 160, 20, 40, 20, Type.풀,1);
			 이상해씨.setSkill(0, 몸통박치기);
			 이상해씨.setSkill(1, 덩굴채찍);
			 이상해씨.setSkill(2, 고속이동);
			 이상해씨.setSkill(3, 솔라빔);
			 이상해씨.learnSkill(0);
			 return 이상해씨;
		case 2 : //파이리
			Starting 파이리 = new Starting("파이리", 140, 40, 20, 30, Type.불,1);
			파이리.setSkill(0, 할퀴기);
			파이리.setSkill(1, 불꽃세례);
			파이리.setSkill(2, 칼춤);
			파이리.setSkill(3, 화염방사);
			파이리.learnSkill(0);
			return 파이리;
		case 3 : //꼬부기
			Starting 꼬부기 = new Starting("꼬부기", 150, 30, 30, 25, Type.물,1);
			꼬부기.setSkill(0, 몸통박치기);
			꼬부기.setSkill(1, 물대포);
			꼬부기.setSkill(2, 방어);
			꼬부기.setSkill(3, 하이드로펌프);
			꼬부기.learnSkill(0);
			return 꼬부기;
		}
		return 디폴트;
	}

	public Pokemon createWild(int number) {
	Pokemon wild = null;

    switch (number) {
        case 1:
            wild = new Pokemon("꼬렛", 70, 20, 20, 30, Type.노말, 2);
            wild.setSkill(0, 몸통박치기);
            wild.setSkill(1, 할퀴기);
            break;

        case 2:
            wild = new Pokemon("모다피", 70, 25, 25, 25, Type.풀, 4);
            wild.setSkill(0, 몸통박치기);
            wild.setSkill(1, 덩굴채찍);
            break;

        case 3:
            wild = new Pokemon("가디", 90, 25, 25, 27, Type.불, 5);
            wild.setSkill(0, 몸통박치기);
            wild.setSkill(1, 불꽃세례);
            break;

        case 4:
            wild = new Pokemon("크랩", 80, 30, 20, 30, Type.물, 6);
            wild.setSkill(0, 몸통박치기);
            wild.setSkill(1, 물대포);
            break;

        case 5:
            wild = new Pokemon("나옹", 80, 40, 15, 25, Type.노말, 10);
            wild.setSkill(0, 할퀴기);
            wild.setSkill(1, 마구할퀴기);
            break;

        case 6:
            wild = new Pokemon("페르시온", 130, 50, 25, 60, Type.노말, 15);
            wild.setSkill(0, 칼춤);
            wild.setSkill(1, 마구할퀴기);
            break;

        case 7:
            wild = new Pokemon("레쿠쟈", 200, 100, 60, 80, Type.노말, 20);
            wild.setSkill(0, 용성군);
            wild.setSkill(1, 화염방사);
            wild.setSkill(2, 하이드로펌프);
            wild.setSkill(3, 솔라빔);
            break;

        default:
            System.out.println("잘못된 포켓몬 번호입니다.");
            break;
    }

    return wild;
}
	
	public void gameOver(Pokemon my) {
		System.out.println(my.name+"가 기절했습니다!");
		System.out.println("배틀을 종료합니다.");
		System.exit(0);  // 프로그램 종료
	}

	public void battle(Pokemon my, Pokemon opp) {
	    Scanner sc = new Scanner(System.in);

	    while (my.checkAlive() && opp.checkAlive()) {
	        Pokemon first = (my.spd >= opp.spd) ? my : opp;
	        Pokemon second = (first == my) ? opp : my;

	        takeTurn(first, second, sc);

	        if (!second.checkAlive()) break;

	        takeTurn(second, first, sc);
	    }

	    if (!my.checkAlive()) {
	        gameOver(my);
	    } else {
	        System.out.println("전투에서 승리했습니다!");
	    }
	    if (my instanceof Starting && opp != null) {
            ((Starting) my).gainExp(opp.lv);  // gainExp 내부에서 자동으로 레벨업 + 기술습득 진행
        }
	}

	public void takeTurn(Pokemon attacker, Pokemon defender, Scanner sc) {
	    Skill selectedSkill;

	    if (attacker instanceof Starting) {
	        System.out.println(attacker.name + "의 차례! 사용할 기술을 고르세요:");
	        showSkill(attacker, defender);
	        int choice = sc.nextInt() - 1;
	        boolean haveSkill = true;
	        haveSkill = checkHaveSkill(attacker, choice);
	        while(!haveSkill)
	        {
	        	System.out.println("유효하지 않은 입력. 다시 고르세요.");
	        	showSkill(attacker, defender);
	        	choice = sc.nextInt() - 1;
	        	haveSkill = checkHaveSkill(attacker, choice);
	        }
	        selectedSkill = attacker.curSkill[choice];
	    } else {
	    	List<Skill> availableSkills = new ArrayList<>();
	    	for (Skill s : attacker.skill) {
	    	    if (s != null) availableSkills.add(s);
	    	}


	    	selectedSkill = availableSkills.get(new Random().nextInt(availableSkills.size()));
	    	System.out.println(attacker.name + "은(는) " + selectedSkill.name + "을(를) 사용했다!");

	    }

	    // 명중률 체크
	    int hit = new Random().nextInt(100) + 1;
	    if (hit > selectedSkill.accuracy) {
	        System.out.println(attacker.name + "의 공격이 빗나갔다!");
	        return;
	    }

	    // 공격 실행
	    attacker.attack(selectedSkill, defender);
	    
	    
	    System.out.println(defender.name + "의 남은 체력: " + Math.max(0, (int) defender.hp));
	    System.out.println();
	}
	
	public void heal(Pokemon pkm) {
		if(healcnt<=2) {
			pkm.hp = pkm.maxHp;
			System.out.println(pkm.name+"의 체력을 회복했습니다.(현재 hp:"+pkm.hp+")");
			healcnt++;
			System.out.println("남은 치료 가능 횟수:"+(3-healcnt)+"회");
		}
		else
		{
			System.out.println("남은 치료 가능 횟수가 없습니다.");
		}
		
	}
	
	private void showSkill(Pokemon attacker, Pokemon defender)
	{
		 for (int i = 0; i < attacker.curSkill.length; i++) {
	            if (attacker.curSkill[i] != null)
	                System.out.println((i + 1) + ". " + attacker.curSkill[i].name);
	        }
	}

	private boolean checkHaveSkill(Pokemon attacker, int choice)
	{
		if(choice < 0 || choice >= attacker.curSkill.length)
		{
			return false;
		}
		
		if(attacker.curSkill[choice] == null)
		{
			return false;
		}
		
		return true;
	}
	

	
	
}

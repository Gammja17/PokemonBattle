package skill;

import gameplay.Type;
import pokemon.Pokemon;

public class 특수기 extends Skill {

	public 특수기(String name, Type type, double power, int accuracy) {
		super(name, type, power, accuracy);   
		this.name = name;
	    this.type = type;
	    this.power = 0;
	    this.accuracy = accuracy;

	}

	@Override
	public void useSkill(Skill skillName, Pokemon target, Pokemon myPoki)
	{
		if(skillName.name.equals("고속이동"))
		{
			myPoki.spdCnt++;
			myPoki.spd += 10;
			System.out.println("슈슉슉... 스피드 10 증가!");
		}
		else if(skillName.name.equals("칼춤"))
		{
			myPoki.atkCnt++;
			myPoki.atk += 10;
			System.out.println("챙 챙... 공격 10 증가!");
		}
		else if(skillName.name.equals("방어")) {
			myPoki.isDefense = true;
			System.out.println("방어 태세... 상대의 다음 공격을 살살 받습니다!");
		}
	}
	
	
	


}

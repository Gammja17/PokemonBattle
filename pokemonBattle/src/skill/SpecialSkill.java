package skill;

import gameplay.*;
import pokemon.Pokemon;
import java.util.function.Consumer;

public class SpecialSkill extends Skill {

	public SpecialSkill(String name, Type type, double power, int accuracy) {
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
			Main.logCallback.accept("슈슉슉... 스피드 10 증가!");
		}
		else if(skillName.name.equals("칼춤"))
		{
			myPoki.atkCnt++;
			myPoki.atk += 10;
			Main.logCallback.accept("챙 챙... 공격 10 증가!");
		}
		else if(skillName.name.equals("방어")) {
			myPoki.isDefense = true;
			Main.logCallback.accept("방어 태세... 상대의 다음 공격을 살살 받습니다!");
		}
	}
	
	
	


}

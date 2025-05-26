package skill;

import gameplay.*;
import pokemon.Pokemon;
import java.util.Random;

public class Skill {
	public String name;
	public Type type;
	public double power;
	public int accuracy;

	public double dmg;
	
	public Skill(String name, Type type, double power, int accuracy) {
		   this.name = name;
	       this.type = type;
	       this.power = power;
	       this.accuracy = accuracy;

	}
	public boolean checkStrongType(Pokemon target)
	{
		if(this.type == Type.불 && target.type == Type.풀)
		{
			return true;
		}
		else if(this.type == Type.물 && target.type == Type.불)
		{
			return true;
		}
		else if(this.type == Type.풀 && target.type == Type.물)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkWeakType(Pokemon target)
	{
		if(this.type == Type.풀 && target.type == Type.불)
		{
			return true;
		}
		else if(this.type == Type.불 && target.type == Type.물)
		{
			return true;
		}
		else if(this.type == Type.물 && target.type == Type.풀)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkDefense(Skill skill) 
	{
		if(skill.name == "방어")
		{
			return true;
		}
		return false;
	}
    
	public boolean checkType(Pokemon myPoki)
	{
		if(myPoki.type == this.type)
		{
			return true;
		}
		return false;
	}
	
	public double vital()
	{
		int n = (int)(Math.random()*1000)%100;
		if(n<5)
		{
			return 2;
		}
		return 1;
	}
	
	public void useSkill(Skill skillName, Pokemon target, Pokemon myPoki) {
	    double damage = skillName.power;
	    damage += myPoki.atk - target.def;

	    boolean isSameType = checkType(myPoki);
	    boolean isStrong = checkStrongType(target);
	    boolean isWeak = checkWeakType(target);
	    boolean isCritical = (vital() == 2.0); // vital()이 2면 급소

	    if (isStrong) damage *= 2;
	    if (isWeak) damage *= 0.5;
	    if (isSameType) damage *= 1.2;
	    damage *= vital();

	    if (damage < 1) damage = 1; // 최소 데미지 보장
	  

	    // 콘솔 출력
	    System.out.println(myPoki.name + "의 " + skillName.name + "!");
	    if (isCritical) System.out.println("급소에 맞았다!");
	    if (isStrong) System.out.println("효과가 굉장하다!");
	    else if (isWeak) System.out.println("효과가 별로다...");
	    
	    if(target.isDefense) {
	    	damage*=0.3;
	    	target.isDefense = false;
	    }
	    
	    target.hp -= damage;

	    System.out.println(target.name + "에게 " + (int) damage + "의 데미지를 입혔다!");
	    
	    
	}

	
	
    
}


package pokemon;

import skill.*;
import gameplay.*;

public class Pokemon {
	
	public String name;
	
	public double hp, atk, def, spd;
	public int lv;
	public Type type;
	public Skill[] skill = new Skill[4];
	public int spdCnt = 0;
	public int atkCnt = 0;
	public boolean isDefense = false;
	
	public Pokemon(String name, double hp, double atk, double def, double spd, Type type, int lv) {
		this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.type = type;
        this.skill = new Skill[4];
        this.lv = lv;
	}
	
	 public void setSkill(int index, Skill skill) {
		 this.skill[index] = skill;
	 }
	
	 
	
	public boolean checkAlive() {
		if(hp<=0) {
			hp=0;
			return false;
		}
		return true;
	}
	
	public void attack(Skill skillName, Pokemon target) {
	    skillName.useSkill(skillName, target, this);
	}
	

}

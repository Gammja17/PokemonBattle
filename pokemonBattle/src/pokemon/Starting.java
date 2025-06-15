package pokemon;

import gameplay.*;
import skill.Skill;
import java.util.function.Consumer;


public class Starting extends Pokemon {
	
	int exp;
	public Runnable onLevelUpCallback;
	
    public Starting(String name, double hp, double atk, double def, double spd, Type type, int lv) {
		super(name, hp, atk, def, spd, type, 1);
		this.exp = 0;
	}

    private final int[] expTable = {0, 2, 5, 9, 14, 20, 27, 35, 44, 54};

    public void learnSkill(int i) {
    	this.curSkill[i] = this.skill[i];
    }
    
    public void gainExp(int gainedExp) {
    	Main.logCallback.accept(name + "이(가) " + gainedExp + "의 경험치를 얻었다!");
        this.exp += gainedExp;
        updateLevel();
    }

    private void updateLevel() {
        for (int i = expTable.length - 1; i >= 0; i--) {
            if (exp >= expTable[i]) {
                int newLevel = i + 1;
                if (newLevel > lv) {
                    int levelGap = newLevel - lv;
                    int oldLevel = lv;
                    lv = newLevel;

                    this.hp += 10 * levelGap;
                    this.atk += 2 * levelGap;
                    this.def += 2 * levelGap;
                    this.spd += 3 * levelGap;
                    this.maxHp += 10 * levelGap;

                    Main.logCallback.accept("레벨이 " + lv + "이(가) 되었습니다!");
                    Main.logCallback.accept("현재 스탯:");
                    Main.logCallback.accept("HP  : " + (int)this.hp);
                    Main.logCallback.accept("ATK : " + (int)this.atk);
                    Main.logCallback.accept("DEF : " + (int)this.def);
                    Main.logCallback.accept("SPD : " + (int)this.spd);
                    
                    if(oldLevel<3 && lv>= 3)
                    {
                    	Main.logCallback.accept(name + " 이(가) 3 레벨이 넘어 " + skill[1].name + " 을 배웠습니다!");
                    	learnSkill(1);
                    }
                    if(oldLevel<6 && lv>= 6)
                    {
                    	Main.logCallback.accept(name + " 이(가) 6 레벨이 넘어 " + skill[2].name + " 을 배웠습니다!");
                    	learnSkill(2);
                    }
                    if(oldLevel<9 && lv>= 9)
                    {
                    	Main.logCallback.accept(name + " 이(가) 9 레벨이 넘어 " + skill[3].name + " 을 배웠습니다!");
                    	learnSkill(3);
                    }
                    if (onLevelUpCallback != null) {
                        onLevelUpCallback.run();
                    }
                }
                break;
            }
        }
    }
    }

    
    



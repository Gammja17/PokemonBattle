package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import gameplay.*;
import pokemon.*;
import skill.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.util.List;

public class BattleWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private GameManager gm;
	private Player p;
	private Pokemon opp = null;
	private JLabel myPkmImg;
	private JLabel enemyPkmImg;
	private JTextArea battleLog;
	private JScrollPane scrollPane;
	private JLabel myPokeLabel;
	private JLabel myHpLabel;
	private JLabel enemyLabel;
	private JLabel enemyHpLabel;
	private JProgressBar enemyHpBar;
	private JProgressBar myHpBar;
	private Starting myPkm = null;
	private JButton[] skillButtons = new JButton[4];
	private boolean playerTurn = true;
	private JButton healBtn;
	private JLabel waveLabel;
	private JButton attackBtn;
	private JButton statusBtn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BattleWindow frame = new BattleWindow(gm, p);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BattleWindow(GameManager gm, Player p) {
		this.p = p;
		this.gm = gm;
		this.myPkm = p.myPkm;
		myPkm.onLevelUpCallback = this::updateSkillButtons;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		myPkmImg = new JLabel(getPokemonImage(p.myPkm.name));
		myPkmImg.setBounds(50, 100, 120, 120);
		contentPane.add(myPkmImg);

		enemyPkmImg = new JLabel();
		enemyPkmImg.setBounds(620, 100, 120, 120);
		contentPane.add(enemyPkmImg);

		battleLog = new JTextArea();
		battleLog.setEditable(false);
		battleLog.setLineWrap(true);
		scrollPane = new JScrollPane(battleLog);
		scrollPane.setBounds(200, 100, 400, 300);
		contentPane.add(scrollPane);

		attackBtn = new JButton("앞으로 나아가기");
		attackBtn.setBounds(150, 450, 150, 30);
		contentPane.add(attackBtn);
		attackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proceedToBattle();
			}
		});

		statusBtn = new JButton("상태확인");
		statusBtn.setBounds(325, 450, 150, 30);
		contentPane.add(statusBtn);
		statusBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        log("====== " + myPkm.name + " 상태 ======");
		        log("레벨: " + myPkm.lv);
		        log("HP: " + (int) myPkm.hp + " / " + (int) myPkm.maxHp);
		        log("공격력: " + myPkm.atk);
		        log("방어력: " + myPkm.def);
		        log("스피드: " + myPkm.spd);
		        log("==========================");
		    }
		});

		healBtn = new JButton("회복");
		healBtn.setBounds(500, 450, 150, 30);
		contentPane.add(healBtn);
		healBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        gm.heal(myPkm);
		        updateMyHp();
		    }
		});

		myPokeLabel = new JLabel(p.myPkm.name);
		myPokeLabel.setBounds(50, 250, 100, 20);
		contentPane.add(myPokeLabel);

		myHpLabel = new JLabel((int) p.myPkm.hp + " / " + (int) p.myPkm.maxHp);
		myHpLabel.setBounds(50, 270, 100, 20);
		contentPane.add(myHpLabel);

		myHpBar = new JProgressBar(0, (int) p.myPkm.maxHp);
		myHpBar.setValue((int) p.myPkm.hp);
		myHpBar.setBounds(50, 290, 120, 15);
		myHpBar.setForeground(Color.GREEN);
		contentPane.add(myHpBar);

		enemyLabel = new JLabel("");
		enemyLabel.setBounds(630, 250, 100, 20);
		contentPane.add(enemyLabel);

		enemyHpLabel = new JLabel("0 / 0");
		enemyHpLabel.setBounds(630, 270, 100, 20);
		contentPane.add(enemyHpLabel);

		enemyHpBar = new JProgressBar(0, 100);
		enemyHpBar.setValue(0);
		enemyHpBar.setBounds(620, 290, 120, 15);
		enemyHpBar.setForeground(Color.RED);
		contentPane.add(enemyHpBar);
		
		waveLabel = new JLabel("Wave: 0");
		waveLabel.setBounds(370, 30, 100, 20);
		contentPane.add(waveLabel);
		
		for (int i = 0; i < 4; i++) {
		    int index = i;
		    skillButtons[i] = new JButton();
		    skillButtons[i].setBounds(100 + i * 150, 500, 140, 30);
		    contentPane.add(skillButtons[i]);

		    int finalI = i;
		    skillButtons[i].addActionListener(e -> {
		        if (myPkm.curSkill[finalI] != null) {
		            playerAttack(finalI);
		        }
		    });
		}
		
		gm.setLogger(msg -> battleLog.append(msg + "\n"));
		DefaultCaret caret = (DefaultCaret) battleLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}

	private void updateEnemyHp(Pokemon opp) {
		enemyHpLabel.setText((int) opp.hp + " / " + (int) opp.maxHp);
		enemyHpBar.setValue((int) opp.hp);
	}

	private ImageIcon getPokemonImage(String name) {
		String path = "/image/";
		switch (name) {
		case "이상해씨":
			path += "bulbasaur_front.png";
			break;
		case "파이리":
			path += "charmander_front.png";
			break;
		case "꼬부기":
			path += "squirtle_front.png";
			break;
		case "꼬렛":
			path += "rattata_front.png";
			break;
		case "모다피":
			path += "bellsprout_front.png";
			break;
		case "가디":
			path += "growlithe_front.png";
			break;
		case "크랩":
			path += "krabby_front.png";
			break;
		case "나옹":
			path += "meowth_front.png";
			break;
		case "페르시온":
			path += "persian_front.png";
			break;
		case "레쿠쟈":
			path += "rayquaza.png";
			break;
		case "메타몽":
			path += "ditto_front.png";
			break;
		}

		ImageIcon icon = new ImageIcon(getClass().getResource(path));
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
	private void log(String text)
	{
		battleLog.append(text + "\n");
		battleLog.setCaretPosition(battleLog.getDocument().getLength());
	}
	private void proceedToBattle() {
		attackBtn.setEnabled(false);
	    Pokemon enemy;
	    int wave = gm.getWave();	    
	    if (wave == 5) {
	        enemy = gm.createWild(6);
	        Main.logCallback.accept("날카로운 고양이 울음소리 같은 것이 들립니다...");
	    } else if (wave == 10) {
	        enemy = gm.createWild(7);
	        Main.logCallback.accept("여정의 끝이 보이는 것 같습니다...");
	        Main.logCallback.accept("공기가 스산합니다.");
	    } else {
	        int num = new Random().nextInt(5) + 1;
	        enemy = gm.createWild(num);
	    }
	    this.opp = enemy;
	    enemyPkmImg.setIcon(getPokemonImage(enemy.name));
        Main.logCallback.accept("야생의 " + enemy.name + "이(가) 나타났다!");

	    updateEnemyInfo(enemy);
	    //gm.battle(p.myPkm, enemy);

	    updateMyHp();
	    updateEnemyHp(enemy);
	    if (myPkm.spdCnt > 0 || myPkm.atkCnt > 0) {
	        myPkm.spd -= myPkm.spdCnt * 10;
	        myPkm.atk -= myPkm.atkCnt * 10;
	        myPkm.spdCnt = 0;
	        myPkm.atkCnt = 0;
	    }
	    startBattle(p.myPkm, enemy);
	}
	private void updateEnemyInfo(Pokemon opp) {
	    enemyLabel.setText(opp.name);
	    enemyHpLabel.setText((int) opp.hp + " / " + (int) opp.maxHp);
	    enemyHpBar.setMaximum((int) opp.maxHp);
	    enemyHpBar.setValue((int) opp.hp);
	    enemyPkmImg.setIcon(getPokemonImage(opp.name));
	}
	private void updateMyHp() {
	    myHpLabel.setText((int) p.myPkm.hp + " / " + (int) p.myPkm.maxHp);
	    myHpBar.setMaximum((int) p.myPkm.maxHp);
	    myHpBar.setValue((int) p.myPkm.hp);
	}
	private void startBattle(Starting my, Pokemon enemy) {
	    this.myPkm = my;
	    this.opp = enemy;
	    
	    if (myPkm.spdCnt > 0 || myPkm.atkCnt > 0) {
	        myPkm.spd -= myPkm.spdCnt * 10;
	        myPkm.atk -= myPkm.atkCnt * 10;
	        myPkm.spdCnt = 0;
	        myPkm.atkCnt = 0;
	    }
	    
	    updateMyHp();
	    updateEnemyHp(opp);
	    updateWaveLabel();
	    playerTurn = true;
	    enableSkillButtons(true);
	    healBtn.setEnabled(true);
	    
	    for (int i = 0; i < 4; i++) {
	        if (myPkm.curSkill[i] != null) {
	            skillButtons[i].setText(myPkm.curSkill[i].name);
	            skillButtons[i].setVisible(true);
	        } else {
	            skillButtons[i].setVisible(false);
	        }
	    }
	    updateSkillButtons();
	   
	}
	private void enableSkillButtons(boolean enable) {
	    for (JButton btn : skillButtons) {
	        btn.setEnabled(enable);
	    }
	}

	private void disableSkillButtons() {
	    for (JButton btn : skillButtons) {
	        btn.setEnabled(false);
	    }
	}
	private void enemyAttack() {
	    List<Skill> available = new ArrayList<>();
	    for (Skill s : opp.skill) {
	        if (s != null) available.add(s);
	    }

	    if (!available.isEmpty()) {
	        Skill chosen = available.get(new Random().nextInt(available.size()));
	        log(opp.name + "의 " + chosen.name + "!");
	        chosen.useSkill(chosen, myPkm, opp);
	        updateMyHp();
	    }

	    if (!myPkm.checkAlive()) {
	        log(myPkm.name + "이(가) 쓰러졌다...");
	        disableSkillButtons();
	        healBtn.setEnabled(false);
	        return;
	    }

	    playerTurn = true;
	    enableSkillButtons(true);
	}
	private void playerAttack(int skillIndex) {
	    if (!playerTurn || myPkm.curSkill[skillIndex] == null) return;

	    Skill selected = myPkm.curSkill[skillIndex];
	    log(myPkm.name + "의 " + selected.name + "!");
	    selected.useSkill(selected, opp, myPkm);

	    updateEnemyHp(opp);
	    
	    if (!opp.checkAlive()) {
	        log(opp.name + "을(를) 쓰러뜨렸다!");
	        int expGained = opp.lv;
	        myPkm.gainExp(expGained);
	        log(myPkm.name + "은(는) 경험치 " + expGained + "를 얻었다!");
	        updateSkillButtons();
	        disableSkillButtons();
	        gm.nextWave();
	        attackBtn.setEnabled(true);
	        log("다음 웨이브를 시작하려면 '앞으로 나아가기' 버튼을 누르세요.");
	        return;
	    }
	    
	    playerTurn = false;
	    disableSkillButtons();

	    // 일정 시간 후 적이 반격
	    Timer timer = new Timer(1000, e -> {
	        enemyAttack();
	    });
	    timer.setRepeats(false);
	    timer.start();
	}	
	private void updateSkillButtons() {
	    for (int i = 0; i < 4; i++) {
	        if (myPkm.curSkill[i] != null) {
	            skillButtons[i].setText(myPkm.curSkill[i].name);
	            skillButtons[i].setVisible(true);
	        } else {
	            skillButtons[i].setVisible(false);
	        }
	    }
	}
	private void updateWaveLabel() {
	    int wave = gm.getWave();
	    waveLabel.setText("Wave: " + wave);
	}
}

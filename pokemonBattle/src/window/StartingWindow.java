package window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gameplay.*;
import pokemon.*;
import skill.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartingWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JLabel profOak;
	private JLabel messageLabel1;
	private JButton nextButton;
	private JLabel selectLabel;
	private JRadioButton bulbasaurBtn;
	private JRadioButton charmanderBtn;
	private JRadioButton squirtleBtn;
	private JButton startButton;
	private JLabel bulbasaurImg;
	private JLabel charmanderImg;
	private JLabel squirtleImg;
	private JPanel bulbasaurPanel;
	private JPanel charmanderPanel;
	private JPanel squirtlePanel;
	private ButtonGroup pokeGroup;
	private JTextArea storyArea;
	private JScrollPane scrollPane;
	
	

	int delay = 500; // 0.5초 = 500밀리초
	Timer storyTimer = new Timer(delay, null);
	final int[] index = {0};
	
	/**
	 * Launch the application.
	 */
	
/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartingWindow frame = new StartingWindow(gm, p);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public StartingWindow(GameManager gm, Player p) {
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(63, 73, 61, 16);
		getContentPane().add(lblNewLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		profOak = new JLabel("");
		ImageIcon originalIcon = new ImageIcon(StartingWindow.class.getResource("/image/profOak.png"));
		Image scaled = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaled);
		profOak.setIcon(resizedIcon);
		profOak.setBounds(20, 20, 180, 180);
		contentPane.add(profOak);

		messageLabel1 = new JLabel("당신의 이름은?");
		messageLabel1.setBounds(220, 30, 250, 30);
		contentPane.add(messageLabel1);

		nameField = new JTextField();
		nameField.setBounds(220, 70, 250, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);

		selectLabel = new JLabel("포켓몬을 선택하세요");
		selectLabel.setBounds(220, 160, 200, 30);
		contentPane.add(selectLabel);

		nextButton = new JButton("다음 ");
		nextButton.setBounds(250, 110, 100, 30);
		contentPane.add(nextButton);

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText().trim();
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
					return;
				}
				p.name = name;
				
				
		        nameField.setVisible(false);
		        nextButton.setVisible(false);
		        messageLabel1.setVisible(false);
		        
		        scrollPane.setVisible(true);
		        storyArea.setText(""); 

		        String[] scriptLines = {
		    		    "???: 신참 트레이너인가...",
		    		    "???: " + p.name + "! 포켓몬의 세계에 잘 왔다.",
		    		    "오박사: 나는 오박사. 포켓몬을 연구하고 있지.",
		    		    "오박사: 너의 첫 포켓몬을 골라보렴!"
		    		};
		        	
		        int delay = 500;
		        Timer storyTimer = new Timer(delay, null);
		        final int[] index = {0};

		        storyTimer.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e1) {
		                if (index[0] < scriptLines.length) {
		                    storyArea.append(scriptLines[index[0]] + "\n");
		                    index[0]++;
		                } else {
		                    storyTimer.stop();
		                    selectLabel.setVisible(true);
		                    bulbasaurPanel.setVisible(true);
		                    charmanderPanel.setVisible(true);
		                    squirtlePanel.setVisible(true);
		                }
		            }
		        });

		        storyTimer.start();

			}
		});

		startButton = new JButton("게임 시작");
		startButton.setBounds(250, 290, 120, 30);
		startButton.setVisible(false);
		contentPane.add(startButton);

		bulbasaurImg = new JLabel("");
		bulbasaurImg.setIcon(new ImageIcon(StartingWindow.class.getResource("/image/bulbasaur_front.png")));
		bulbasaurImg.setBounds(30, 200, 70, 70);

		charmanderImg = new JLabel("");
		charmanderImg.setIcon(new ImageIcon(StartingWindow.class.getResource("/image/charmander_front.png")));
		charmanderImg.setBounds(230, 200, 70, 70);

		squirtleImg = new JLabel("");
		squirtleImg.setIcon(new ImageIcon(StartingWindow.class.getResource("/image/squirtle_front.png")));
		squirtleImg.setBounds(420, 200, 70, 70);

		bulbasaurPanel = new JPanel();
		bulbasaurPanel.setLayout(new BoxLayout(bulbasaurPanel, BoxLayout.Y_AXIS));
		bulbasaurPanel.setBounds(50, 200, 100, 100);
		bulbasaurPanel.setOpaque(false);

		bulbasaurImg = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/image/bulbasaur_front.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		bulbasaurImg.setAlignmentX(Component.CENTER_ALIGNMENT);

		bulbasaurBtn = new JRadioButton("이상해씨");
		bulbasaurBtn.setHorizontalAlignment(SwingConstants.CENTER);
		bulbasaurBtn.setOpaque(false);
		bulbasaurBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		bulbasaurPanel.add(bulbasaurImg);
		bulbasaurPanel.add(bulbasaurBtn);
		contentPane.add(bulbasaurPanel);
		bulbasaurPanel.setVisible(false);

		charmanderPanel = new JPanel();
		charmanderPanel.setLayout(new BoxLayout(charmanderPanel, BoxLayout.Y_AXIS));
		charmanderPanel.setBounds(240, 200, 100, 100);
		charmanderPanel.setOpaque(false);
		

		charmanderImg = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/image/charmander_front.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		charmanderImg.setAlignmentX(Component.CENTER_ALIGNMENT);

		charmanderBtn = new JRadioButton("파이리");
		charmanderBtn.setOpaque(false);
		charmanderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		charmanderPanel.add(charmanderImg);
		charmanderPanel.add(charmanderBtn);
		contentPane.add(charmanderPanel);
		charmanderPanel.setVisible(false);

		squirtlePanel = new JPanel();
		squirtlePanel.setLayout(new BoxLayout(squirtlePanel, BoxLayout.Y_AXIS));
		squirtlePanel.setBounds(420, 200, 100, 100);
		squirtlePanel.setOpaque(false);

		squirtleImg = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/image/squirtle_front.png"))
				.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		squirtleImg.setAlignmentX(Component.CENTER_ALIGNMENT);

		squirtleBtn = new JRadioButton("꼬부기");
		squirtleBtn.setOpaque(false);
		squirtleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		squirtlePanel.add(squirtleImg);
		squirtlePanel.add(squirtleBtn);
		contentPane.add(squirtlePanel);
		squirtlePanel.setVisible(false);

		pokeGroup = new ButtonGroup();
		pokeGroup.add(bulbasaurBtn);
		pokeGroup.add(charmanderBtn);
		pokeGroup.add(squirtleBtn);
		
		ActionListener selectionListener = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedName = "";

		        if (bulbasaurBtn.isSelected()) selectedName = "이상해씨";
		        else if (charmanderBtn.isSelected()) selectedName = "파이리";
		        else if (squirtleBtn.isSelected()) selectedName = "꼬부기";

		        storyArea.append("오박사: 오! " + selectedName + "를 골랐구나.\n");
		        storyArea.append("오박사: 정말 훌륭한 선택이야!\n");
		        storyArea.append("오박사: 앞으로 펼쳐질 여정이 기대되는걸?\n");

		        startButton.setVisible(true);  
		    }
		};

		bulbasaurBtn.addActionListener(selectionListener);
		charmanderBtn.addActionListener(selectionListener);
		squirtleBtn.addActionListener(selectionListener);
		
		storyArea = new JTextArea();
		storyArea.setLineWrap(true);
		storyArea.setWrapStyleWord(true);
		storyArea.setEditable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 30, 340, 140);
		storyArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		scrollPane.setVisible(false);
		
		scrollPane.setViewportView(storyArea);
		contentPane.add(scrollPane);
		

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = -1;
				if (bulbasaurBtn.isSelected())
					selected = 1;
				else if (charmanderBtn.isSelected())
					selected = 2;
				else if (squirtleBtn.isSelected())
					selected = 3;

				if (selected == -1) {
					JOptionPane.showMessageDialog(null, "포켓몬을 선택해주세요.");
					return;
				}

				p.myPkm = gm.selectStarting(selected);
			
		        selectLabel.setVisible(false);
		        bulbasaurPanel.setVisible(false);
		        charmanderPanel.setVisible(false);
		        squirtlePanel.setVisible(false);
		        startButton.setVisible(false);

		        storyArea.setText("");

		        String[] secondScript = {
		            "오박사: " + p.myPkm.name + "구나. 좋은 포켓몬이지!",
		            "오박사: 전설의 포켓몬 레쿠쟈가 폭주하고 있어...",
		            "오박사: 네가 막아주었으면 좋겠구나.",
		            "오박사: 앞으로의 여정이 찬란하기를!"
		        };

		        Timer secondTimer = new Timer(500, null);
		        final int[] idx = {0};
		        secondTimer.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		                if (idx[0] < secondScript.length) {
		                    storyArea.append(secondScript[idx[0]] + "\n");
		                    idx[0]++;
		                } else {
		                    secondTimer.stop();
		                    startButton.setVisible(true);
		                    new BattleWindow(gm, p).setVisible(true);
		                    dispose();
		                }
		            }
		        });
		        secondTimer.start();
			}
		});
		
	

	}
}

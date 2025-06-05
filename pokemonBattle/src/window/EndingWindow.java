package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import gameplay.Player;

public class EndingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea storyArea;
	private JScrollPane scrollPane;
    private JButton yesButton, noButton;
    private Player player;

	/**
	 * Launch the application.
	 */
	

	
	private final String[] script = {
	        "레쿠쟈: 키에에에에에엑!!!",
	        "레쿠쟈가 몸부림칩니다.",
	        "갑자기, 레쿠쟈의 몸이 빛나기 시작했습니다.",
	        "당신은 레쿠쟈를 지켜봅니다. 그리고, 이내 형상이 변하기 시작합니다.",
	        "???: 에에---- 메---",
	        "메타몽: 메...",
	        "폭주한 레쿠쟈의 정체는 사실 메타몽이었습니다!!!",
	        "",
	        "오박사: %s!!! 괜찮나?!",
	        "메타몽: 메타...",
	        "오박사: 폭주한 레쿠쟈의 정체가 사실은 변신한 메타몽이었다고?! 정말 놀랄 노자로군.",
	        "오박사: 수고 많았네. %s, %s.",
	        "오박사: 어엿한 포켓몬 트레이너가 되었어... 앞으로도 여정을 계속할 텐가?"
	    };
	private int index = 0;
	
	/**
	 * Create the frame.
	 */
	public EndingWindow(Player p) {
		this.player = p;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		storyArea = new JTextArea();
		storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        scrollPane = new JScrollPane(storyArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        yesButton = new JButton("예");
        noButton = new JButton("아니오");
        yesButton.setVisible(false);
        noButton.setVisible(false);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, null); // 1초 간격
        timer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index < script.length) {
                    String line = script[index];
                    if (line.contains("%s")) {
                        if (line.contains("%s, %s")) {
                            line = String.format(line, player.name, player.myPkm.name);
                        } else {
                            line = String.format(line, player.name);
                        }
                    }
                    storyArea.append(line + "\n");
                    index++;
                } else {
                    timer.stop();
                    yesButton.setVisible(true);
                    noButton.setVisible(true);
                }
            }
        });
        timer.start();

        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storyArea.append("\n오박사: 그래... 앞으로도 펼쳐질 여정이 찬란하기를!\n");
                storyArea.append(player.myPkm.name + ": 캬아!\n");
                storyArea.append(player.name + "(와)과 " + player.myPkm.name + "는 또다른 여행을 떠났습니다!!\n");
                storyArea.append("\n========The End========\n");
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storyArea.append("\n" + player.myPkm.name + ": 캬아!\n");
                storyArea.append("오박사: 허허. " + player.myPkm.name + "는 생각이 다른 것 같은데?\n");
                storyArea.append("오박사: 어찌 되었건... 앞으로도 펼쳐질 여정이 찬란하기를!\n");
                storyArea.append(player.name + "(와)과 " + player.myPkm.name + "는 또다른 여행을 떠났습니다!!\n");
                storyArea.append("\n========The End========\n");
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            }
        });
	}

}

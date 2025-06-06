package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import gameplay.Player;

public class EndingWindow extends JFrame {
	

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea storyArea;
    private JScrollPane scrollPane;
    private JButton yesButton, noButton;
    private JLabel imageLabel;
    private JLabel sideImageLabel;
    private JLabel endingImageLabel;
    private Player player;

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

    public EndingWindow(Player p) {
        this.player = p;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        imageLabel = new JLabel();
        imageLabel.setBounds(300, 20, 200, 200);
        contentPane.add(imageLabel);
        setPokemonImage("rayquaza.png");

        sideImageLabel = new JLabel();
        sideImageLabel.setBounds(550, 20, 200, 200);
        contentPane.add(sideImageLabel);

        endingImageLabel = new JLabel();
        endingImageLabel.setBounds(260, 10, 280, 200);
        endingImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endingImageLabel.setVisible(false);
        contentPane.add(endingImageLabel);


        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

        scrollPane = new JScrollPane(storyArea);
        scrollPane.setBounds(100, 230, 600, 250);
        contentPane.add(scrollPane);

        
        DefaultCaret caret = (DefaultCaret) storyArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


        yesButton = new JButton("예");
        noButton = new JButton("아니오");
        yesButton.setBounds(250, 500, 100, 40);
        noButton.setBounds(450, 500, 100, 40);
        yesButton.setVisible(false);
        noButton.setVisible(false);
        
        contentPane.add(yesButton);
        contentPane.add(noButton);

        
        Timer timer = new Timer(1000, null);
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

                    // 메타몽 이미지 전환
                    if (line.startsWith("???: 에에---- 메---")) {
                        setPokemonImage("ditto_front.png");
                    }

                    // 오박사 이미지 표시
                    if (line.startsWith("오박사:") && line.contains("괜찮나?!")) {
                        setSideImage("profOak.png");
                    }

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
                hideSideImages();
                showEndingPokemonImage();
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
                hideSideImages();
                showEndingPokemonImage();
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
            }
        });
    }

    private void setPokemonImage(String filename) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/" + filename));
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    private void setSideImage(String filename) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/image/" + filename));
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        sideImageLabel.setIcon(new ImageIcon(img));
    }

    private void hideSideImages() {
        sideImageLabel.setIcon(null);
        imageLabel.setIcon(null);
    }

    private void showEndingPokemonImage() {
        String starterImage = "";
        switch (player.myPkm.name) {
            case "이상해씨":
                starterImage = "end_bulbasaur.png";
                break;
            case "꼬부기":
                starterImage = "end_squirtle.png";
                break;
            case "파이리":
                starterImage = "end_charmander.png";
                break;
            default:
                starterImage = "";
                break;
        }
        if (!starterImage.isEmpty()) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/image/" + starterImage));
            Image img = icon.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);
            endingImageLabel.setIcon(new ImageIcon(img));
            endingImageLabel.setVisible(true);
            endingImageLabel.repaint();
            endingImageLabel.revalidate();
        }
    }
}

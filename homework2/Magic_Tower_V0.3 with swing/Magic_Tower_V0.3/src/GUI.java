import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI {
	GameData gameData;
	JFrame f;
	JLabel[][] b;

	//增加两个Panel，原本游戏UI放在gamePanel中，菜单放在menuPanel中，方便切换
	JPanel gamePanel = new JPanel();
	JPanel menuPanel = new JPanel(new GridBagLayout());
	JLayeredPane layeredPane = new JLayeredPane();


	GUI(GameData gameData) {

		//布局管理器，与之前f的null布局相同，为默认的borderlayout
		gamePanel.setLayout(null);

		this.gameData = gameData;

		f = new JFrame("Magic Tower");

		b = new JLabel[gameData.H][gameData.W];

		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				b[i][j]=new JLabel();
				b[i][j].setBounds(j*100, i*100, 100, 100);
				gamePanel.add(b[i][j]);//改为gamePanel
			}
		}


		//创建menuPanel
		menuCreate();

		gamePanel.setBounds(0, 0, gameData.W * 100, gameData.H * 100);
		menuPanel.setBounds(0, 0, gameData.W * 100, gameData.H * 100);

		layeredPane.add(gamePanel,JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(menuPanel,JLayeredPane.POPUP_LAYER);
		layeredPane.setVisible(true);

		gamePanel.setVisible(true);
		menuPanel.setVisible(false);

		f.setContentPane(layeredPane);

		f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_M) {  // 按下 'M' 键显示菜单
					menuPanel.setVisible(!menuPanel.isVisible());
				}
			}
		});

		refreshGUI();

		JButton menubutton = new JButton("MENU");
		menubutton.setBounds(gameData.W * 100 - 100, gameData.H * 100 - 100, 100, 100);

		gamePanel.add(menubutton);

		menubutton.addActionListener(e -> {
			menuPanel.setVisible(!menuPanel.isVisible());
			f.requestFocusInWindow();
		});



		f.setSize(gameData.H*100+10, gameData.W*100+40);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.requestFocusInWindow();
	}
	public void refreshGUI()
	{
		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				Image scaledImage = chooseImage(gameData.map[gameData.currentLevel][i][j]);
				b[i][j].setIcon(new ImageIcon(scaledImage));
			}
		}

	}
	private static Image chooseImage(int index){
		ImageIcon[] icons = new ImageIcon[10];
		Image scaledImage;
		icons[0]= new ImageIcon("Wall.jpg");
		icons[1]= new ImageIcon("Floor.jpg");
		icons[2]= new ImageIcon("Key.jpg");
		icons[3]= new ImageIcon("Door.jpg");
		icons[4]= new ImageIcon("Stair.jpg");
		icons[5]= new ImageIcon("Exit.jpg");
		icons[6]= new ImageIcon("Hero.jpg");
		icons[7]= new ImageIcon("Potion.jpg");
		icons[8]= new ImageIcon("Monster.jpg");
		if(index>10)
			scaledImage = icons[7].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		else if(index<0)
			scaledImage = icons[8].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		else
			scaledImage = icons[index].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		return scaledImage;
	}

	private void menuCreate(){

		//设置UI风格
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}


		menuPanel.setBackground(new Color(0, 0, 0, 50));
		menuPanel.setOpaque(false);

		GridBagConstraints gbc = new GridBagConstraints();

		JLabel titleLabel = new JLabel("MENU",SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial",Font.BOLD,70));
		titleLabel.setForeground(Color.WHITE);

		JLabel titleLabel2 = new JLabel("Press M back to game",SwingConstants.CENTER);
		titleLabel2.setFont(new Font("Arial",Font.BOLD,20));
		titleLabel2.setForeground(Color.WHITE);


		JButton back = new JButton("back");
		JButton restart = new JButton("restart");
		JButton save = new JButton("save");
		JButton load = new JButton("load");
		JButton quit = new JButton("quit");

		setButtonStyle(new JButton[]{back,restart,save,load,quit});

		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridy = 0;
		gbc.insets = new Insets(20,0,5,0);
		titleLabel.setPreferredSize(new Dimension(500, 50));
		menuPanel.add(titleLabel,gbc);

		gbc.gridy++;
		gbc.insets = new Insets(5,0,5,0);
		titleLabel.setPreferredSize(new Dimension(500, 50));
		menuPanel.add(titleLabel2,gbc);

		gbc.gridy++;
		gbc.insets = new Insets(10,0,20,0);
		titleLabel.setPreferredSize(new Dimension(500, 50));
		menuPanel.add(back,gbc);

		gbc.gridy++;
		gbc.insets = new Insets(10,0,20,0);
		menuPanel.add(restart,gbc);

		gbc.gridy++;
		gbc.insets = new Insets(10,0,20,0);
		menuPanel.add(save,gbc);

		gbc.gridy++;
		gbc.insets = new Insets(10,0,20,0);
		menuPanel.add(load,gbc);

		gbc.gridy++;
		gbc.insets = new Insets(10,0,20,0);
		menuPanel.add(quit,gbc);

		back.addActionListener(e -> {
			menuPanel.setVisible(false);
			f.requestFocusInWindow();
		});

		restart.addActionListener(e -> {
			menuPanel.setVisible(false);
			Menu.restartGame();
			f.requestFocusInWindow();
		});

		save.addActionListener(e -> {
			menuPanel.setVisible(false);
			Menu.saveGame();
			f.requestFocusInWindow();
		});

		load.addActionListener(e -> {
			menuPanel.setVisible(false);
			Menu.loadGame();
			f.requestFocusInWindow();
		});

		quit.addActionListener(e -> {
			menuPanel.setVisible(false);
			Menu.quitGame();
			f.requestFocusInWindow();
		});

	}


	private void setButtonStyle(JButton[] buttons){
		for (JButton button : buttons) {
			button.setFont(new Font("Arial",Font.BOLD,30));
			button.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
		}
	}
}

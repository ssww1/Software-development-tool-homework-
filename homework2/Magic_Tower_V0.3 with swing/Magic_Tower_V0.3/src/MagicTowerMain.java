public class MagicTowerMain {
	static GameData gameData;
	static GameControl gameControl;
	static Menu menu;
	static GUI gui;

	public static void main(String[] args) {
		gameData= new GameData();
		gameData.readMapFromFile("Map.in");
		gameData.printMap();
		gui=new GUI(gameData);
		//把gui传进menu，方便调用refreshGUI方法
		menu=new Menu(gameData,gui);
		menu.loadMenu("Menu.XML");
		gameControl=new GameControl(gameData,menu,gui);
		gameControl.gameStart();
	}

}
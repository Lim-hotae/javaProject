import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class App extends JFrame {
	private Container contentPane;
	private TreeNode root;
	private JPanel panelRL;
	private JPanel panelRRN;
	private JTextField pt;
	private JTextField px;
	private JTextField py;
	private JTextField pw;
	private JTextField ph;
	private JTextField pc;
	private int tempx = 0;
	private int tempy = 0;
	private boolean dragged = false;
	private boolean IsEdgeTouched = false;
	private int Edge; // -2:우변 -1:좌변 1:윗변 2:아래변
	private int orix, oriy;
	private Color temp = new Color(0, 0, 0);
	private ArrayList<int[]> colors = new ArrayList<>(); // 'r,g,v 정보를갖는 원소3개 배열'을 원소로 갖는 arraylist : arraylist 인덱스는
	protected static JTextArea t;

	// level 에 대응됨
	private static int start = 1;

	///// App 생성부
	public App() {

	}

	public App(int start) {
		setTitle("파일디렉터리"); // 미구현(디렉터리 정보 받아와야함)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		///// color 초기화 : 랜덤한 r,g,v 생성
		Random randomGenerator = new Random(); // int type 0~255 랜덤수 생성
		int max = 1000; // 저장 가능 level 의 한도를 1000개로 한정

		while (max != 0) {
			int[] RGB = new int[3];

			RGB[0] = randomGenerator.nextInt(256);
			RGB[1] = randomGenerator.nextInt(256);
			RGB[2] = randomGenerator.nextInt(256);

			colors.add(RGB);
			max--;
		}

		///// 기본 패널
		contentPane = getContentPane();

		///// 3개 영역 분학
		JSplitPane splitPaneL = new JSplitPane();
		contentPane.add(splitPaneL);

		JSplitPane splitPaneR = new JSplitPane();
		splitPaneL.setRightComponent(splitPaneR);

		///// 3개 영역에 패널 부착
		JPanel panelL = new JPanel();
		panelL.setPreferredSize(new Dimension(300, 1500));
		splitPaneL.setLeftComponent(panelL);
		panelL.setLayout(new BorderLayout());

		panelRL = new JPanel();
		panelRL.setPreferredSize(new Dimension(900, 1500));
		splitPaneR.setLeftComponent(panelRL);
		panelRL.setLayout(new BorderLayout());

		JPanel panelRR = new JPanel();
		panelRR.setPreferredSize(new Dimension(100, 1500));
		splitPaneR.setRightComponent(panelRR);
		panelRR.setLayout(new BorderLayout());

		///// 좌측 패널 구현

		// 라벨 부착
		JPanel panelLN = new JPanel();
		panelLN.setBackground(new Color(50, 100, 250));
		panelL.add(panelLN, BorderLayout.NORTH);
		JLabel LN = new JLabel("Text Editor Pane");
		LN.setBorder(new LineBorder(Color.BLACK));
		LN.setForeground(Color.WHITE);
		panelLN.add(LN);

		// 텍스트영역 부착
		JPanel panelLC = new JPanel();
		panelLC.setBackground(new Color(50, 100, 250));
		panelL.add(panelLC, BorderLayout.CENTER);

		t = new JTextArea("", 50, 30);
		t.setBackground(new Color(50, 100, 250));
		panelLC.add(new JScrollPane(t));

		// 버튼 부착(적용)
		JPanel panelLS = new JPanel();
		panelLS.setBackground(new Color(50, 100, 250));
		panelL.add(panelLS, BorderLayout.SOUTH);
		JButton btnL = new JButton("적용");
		btnL.setBackground(Color.RED);
		panelLS.add(btnL);
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Tree 형성부

				String temp = t.getText();
				String lines[] = temp.split("(\\r?\\n)");

				TreeNode prev;
				int levelprev = 0;
				int levelafter = 0;

				root = new TreeNode(lines[0]); // root node
				root.assignLevel(0);
				prev = root;

				for (int i = 1; i < lines.length; i++) {
					levelafter = 0;

					while (true) {

						if (lines[i].startsWith("\t")) {
							lines[i] = lines[i].replaceFirst("\t", "");
							levelafter++;
						}

						else {

							if (levelafter > levelprev) {
								prev = new TreeNode(prev, lines[i]);
								prev.assignLevel(levelafter);
								break;
							}

							else if (levelafter == levelprev) {
								prev = new TreeNode(prev.getParent(), lines[i]);
								prev.assignLevel(levelafter);
								break;
							}

							else {
								prev = new TreeNode(prev.getParent().getParent(), lines[i]);
								prev.assignLevel(levelafter);
								break;
							}
						}
					}

					levelprev = levelafter;
				}

				// Tree 위치 설정

				root.assignPos(450, 450);
				root.assignRadius(200);

				AssignPosRadius(root);

				// Tree 시각화

				Visualize(root);
				panelRL.repaint();
			}
		});

		///// 가운데 패널 구현
		JLabel RL = new JLabel("Mind Map Pane");
		RL.setBorder(new LineBorder(Color.BLACK));
		RL.setForeground(Color.WHITE);
		panelRL.setLayout(null); // 배치관리자 없이 배치
		RL.setBounds(400, 0, 100, 25);
		panelRL.add(RL);
		panelRL.setBackground(new Color(210, 230, 250));

		///// 우측 패널 구현

		// 라벨 부착
		panelRRN = new JPanel();
		panelRRN.setBackground(new Color(230, 245, 250));
		panelRR.add(panelRRN, BorderLayout.NORTH);
		JLabel RRN = new JLabel("Attribute Pane");
		RRN.setBorder(new LineBorder(Color.BLACK));
		RRN.setForeground(Color.WHITE);
		panelRRN.add(RRN);

		// 버튼 부착(변경)
		JPanel panelRRS = new JPanel();
		panelRRS.setBackground(new Color(230, 245, 250));
		JButton btnRRS = new JButton("변경");
		btnRRS.setBackground(Color.RED);
		panelRR.add(panelRRS, BorderLayout.SOUTH);
		panelRRS.add(btnRRS);

		// 속성 패널 5개 부착
		JPanel panelRRC = new JPanel();
		panelRRC.setBackground(new Color(230, 245, 250));
		panelRR.add(panelRRC, BorderLayout.CENTER);
		panelRRC.setLayout(new GridLayout(6, 1));

		JPanel p1 = new JPanel();
		p1.setBackground(new Color(230, 245, 250));
		pt = new JTextField("", 15);
		pt.setBackground(Color.GRAY);
		p1.setLayout(new FlowLayout());
		p1.add(new JLabel("TEXT :   "));
		p1.add(new JScrollPane(pt));
		panelRRC.add(p1);

		JPanel p2 = new JPanel();
		p2.setBackground(new Color(230, 245, 250));
		px = new JTextField("", 15);
		p2.setLayout(new FlowLayout());
		p2.add(new JLabel("X :         "));
		p2.add(new JScrollPane(px));
		panelRRC.add(p2);

		JPanel p3 = new JPanel();
		p3.setBackground(new Color(230, 245, 250));
		py = new JTextField("", 15);
		p3.setLayout(new FlowLayout());
		p3.add(new JLabel("Y :          "));
		p3.add(new JScrollPane(py));
		panelRRC.add(p3);

		JPanel p4 = new JPanel();
		p4.setBackground(new Color(230, 245, 250));
		pw = new JTextField("", 15);
		p4.setLayout(new FlowLayout());
		p4.add(new JLabel("W :         "));
		p4.add(new JScrollPane(pw));
		panelRRC.add(p4);

		JPanel p5 = new JPanel();
		p5.setBackground(new Color(230, 245, 250));
		ph = new JTextField("", 15);
		p5.setLayout(new FlowLayout());
		p5.add(new JLabel("H :         "));
		p5.add(new JScrollPane(ph));
		panelRRC.add(p5);

		JPanel p6 = new JPanel();
		p6.setBackground(new Color(230, 245, 250));
		pc = new JTextField("", 15);
		p6.setLayout(new FlowLayout());
		p6.add(new JLabel("Color :     "));
		p6.add(new JScrollPane(pc));
		panelRRC.add(p6);

		//
		createMenu();
		//
		createToolBar();
		//

		setSize(1500, 700);
		setVisible(true);
	}

	///// Menu
	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		MenuApp menuapp = new MenuApp(mb);

		setJMenuBar(mb);
	}

	///// ToolBar
	public void createToolBar() {

		JToolBar toolBar = new JToolBar("Tool Bar");
		new ToolbarApp(toolBar);

		contentPane.add(toolBar, BorderLayout.NORTH);
	}

	public void AssignPosRadius(TreeNode node)
	   {
		  int n = node.getChildren().size();
		  Random angle = new Random();	// 랜덤한 각도
		  
		  if(n==0) return;
		   
		  for(TreeNode child:node.getChildren())
		  {
			  double x;
			  double y;
			  int limit=0;
			  
			  while(true)	// 부모노드 영역(원) 밖의 점을 선택 시도
			  {
				  int Angle = angle.nextInt((int)Math.PI*2);
				  
				  x = node.getX()+node.getRadius()*Math.cos(Angle);
				  y = node.getY()+node.getRadius()*Math.sin(Angle);
				  
				  if(node.getParent()==node)
				  {
				  	if(IsBalancedLength(node,x,y)) break;
				  }
				  
				  else
				  {
					  if(OutofCircle(node.getParent(),x,y)&&IsBalancedLength(node,x,y)) break;
				  }
				  
				  limit++;
				  if(limit==100000) break;
				  
			  }
			  
			  child.assignPos(x,y);
			  child.assignRadius((node.getRadius())*0.35);
			  
			  //재귀
			  AssignPosRadius(child);
			  
		  }
	   }
	   
	   public boolean IsBalancedLength(TreeNode parent, double x, double y)
	   {
		   boolean IsBalanced = true;
		   double minlen = 2*parent.getRadius()*Math.sin(Math.PI/parent.getChildren().size());
		   
		   for(TreeNode child:parent.getChildren())
		   {
			   double len = Math.sqrt( Math.pow(x-child.getX(),2)+Math.pow(y-child.getY(),2) );
			   
			   if(len<minlen*0.2&&(int)child.getX()!=-1)
			   {
				   IsBalanced = false;
				   break;
			   }
		   }
		   return IsBalanced;
	   }
	   
	   public boolean OutofCircle(TreeNode parent ,double x, double y)
	   {
			   
		   double px = parent.getX();
		   double py = parent.getY();
		   double pr = parent.getRadius();
		   
		   if(Math.pow(px-x,2)+Math.pow(py-y,2)-Math.pow(pr,2)/2>0)	//	영역 외부 존재 할 경우	
			{
			    return true;
			}
		   
		   else return false;
	   }
	   
	   
	   ///// 시각화
	   public void Visualize(TreeNode node)
	   {
		   final MindMapNode mmn = new MindMapNode(node.getLabel());
		   
		   MouseListener MyMouseListener = new MouseListener()
		   {
			   public void mousePressed(MouseEvent e)
			   {
				   orix = mmn.getX();
				   oriy = mmn.getY();
				   
					   
				   Color reverseColor = new Color(255-mmn.getColor().getRed(),255-mmn.getColor().getGreen(),255-mmn.getColor().getBlue());
				   temp = mmn.getColor();
				   mmn.getPanel().setBackground(reverseColor);
				   
				   pt.setEditable(true);
				   pt.setText(mmn.getStringLabel());
				   pt.setEditable(false);
				   
				   px.setText(String.valueOf(mmn.getX()));
				   py.setText(String.valueOf(mmn.getY()));
				   pw.setText(String.valueOf(mmn.getW()));
				   ph.setText(String.valueOf(mmn.getH()));
				   pc.setText(String.format("%02X",mmn.getColor().getRed())+String.format("%02X",mmn.getColor().getGreen())+String.format("%02X",mmn.getColor().getBlue()));
				   
				   panelRL.repaint();
			   }
			   
			   public void mouseReleased(MouseEvent e)	//
			   {
				   mmn.getPanel().setBackground(temp);
				   panelRL.remove(mmn.getPanel());
				   
				   if(IsEdgeTouched == true)	//	노드크기 변경 : 노드 모서리를 누른경우
				   {
					   switch(Edge)
					   {
					   //우변
						   case 1:
							   
							   mmn.setW(mmn.getW()+2*(tempx-(orix+mmn.getW()/2)));
							   mmn.setPos(orix+tempx-(orix+mmn.getW()/2),oriy);  
							   
							   break;
					   //좌변
						   case 2:
							   
							   mmn.setW(mmn.getW()+2*(-tempx+(orix-mmn.getW()/2)));
							   mmn.setPos(orix-tempx+(orix-mmn.getW()/2),oriy);
							   
							   break;
						//윗변	
						   case 3:
							   
							   mmn.setH(mmn.getH()+2*(-tempy+(oriy-mmn.getH()/2)));
							   mmn.setPos(orix,oriy-tempy+(oriy-mmn.getH()/2));
							   
							   break;
						//아래변	   
						   case 4:
							   
							   mmn.setH(mmn.getH()+2*(tempy-(oriy+mmn.getH()/2)));
							   mmn.setPos(orix,oriy+tempy-(oriy+mmn.getH()/2));
							   
							   break;
					   }
					   
					   mmn.setPanelBounds(mmn.getW(),mmn.getH());

					   panelRL.add(mmn.getPanel());
					   //
					   px.setText(String.valueOf(mmn.getX()));
					   py.setText(String.valueOf(mmn.getY()));
					   pw.setText(String.valueOf(mmn.getW()));
					   ph.setText(String.valueOf(mmn.getH()));
					   
					   panelRL.repaint();
					   
					   IsEdgeTouched = false;
					   Edge = -1;
					   
					   return;
				   }
				   
				   if(dragged==false)	mmn.setPos(orix,oriy);
				   else mmn.setPos(orix+tempx-mmn.getW()/2, oriy+tempy-mmn.getH()/2);
				   
				   dragged = false;
				   
				   mmn.setPanelBounds(mmn.getW(),mmn.getH());

				   panelRL.add(mmn.getPanel());
				   //
				   px.setText(String.valueOf(mmn.getX()));
				   py.setText(String.valueOf(mmn.getY()));
				   pw.setText(String.valueOf(mmn.getW()));
				   ph.setText(String.valueOf(mmn.getH()));
				   
				   panelRL.repaint();
			   }
			   
			   public void mouseEntered(MouseEvent e) {}
			   public void mouseExited(MouseEvent e) {}
			   public void mouseClicked(MouseEvent e) {}		   
		   };
		   
		   MouseMotionListener MyMouseMotionListener = new MouseMotionListener()
		   {
			   public void mouseDragged(MouseEvent e)
			   {
				   tempx = e.getX();
				   tempy = e.getY();
				   dragged = true;
			   }
			   
			   public void mouseMoved(MouseEvent e) {}
		   };
		   
		   mmn.getPanel().addMouseListener(MyMouseListener);
		   mmn.getPanel().addMouseMotionListener(MyMouseMotionListener);
		   
		   int[] rgb = this.colors.get(node.getLevel());	// 해당노드 레벨에 맞는 rgb 가져오기
		   
		   mmn.setBackGround(rgb[0], rgb[1], rgb[2]);
		   mmn.setColor(new Color(rgb[0],rgb[1],rgb[2]));
		   mmn.setPos((int)node.getX(),(int)node.getY());
		   mmn.setLabelSize((int)node.getW(), (int)node.getH());
		   mmn.setPanelBounds((int)node.getW(), (int)node.getH());
		   
		   panelRL.add(mmn.getPanel());

		   //	재귀
		   
		   if(node.getChildren().size() == 0) return;
		   
		   for(TreeNode child:node.getChildren())
		   {
			   Visualize(child);
		   }
	   }
	   

	public static void main(String[] args) {

		new App(start);
	}
}
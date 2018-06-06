import java.util.*;

public class TreeNode {
	private TreeNode Parent;
	private String label;
	private int level = -1; // 초기화되지않음
	private double x = -1; // 초기화되지않음
	private double y = -1; // 초기화되지않음
	private double radius = -1; // 초기화되지않음
	private double w = 25; // 기본값
	private double h = 25; // 기본값
	private ArrayList<TreeNode> Children = new ArrayList<>();

	public TreeNode(String root) {
		this.label = root;
		this.Parent = this;
	}

	public TreeNode(TreeNode parent, String child) {
		this.Parent = parent;
		this.Parent.Children.add(this);
		this.label = child;
	}

	public TreeNode getParent() {
		return this.Parent;
	}

	public ArrayList<TreeNode> getChildren() {
		return this.Children;
	}

	public void assignPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void assignRadius(double radius) {
		this.radius = radius;
	}

	public void assignLevel(int level) {
		this.level = level;
	}

	public double getRadius() {
		return this.radius;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public String getLabel() {
		return this.label;
	}

	public double getW() {
		return this.w;
	}

	public double getH() {
		return h;
	}

	public int getLevel() {
		return this.level;
	}
}
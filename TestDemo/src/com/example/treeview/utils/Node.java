package com.example.treeview.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	// ���ڵ�
	private int pid = 0;
	private String name;
	// ����
	private int level;
	//�Ƿ�չ��
	private boolean isExpand;
	private int icon;
	
	private Node parent;
	private List<Node> child = new ArrayList<Node>();
	
	public Node(int id, int pid, String label)
	{
		this.id = id;
		this.pid = pid;
		this.name = label;
	}
	
	/*
	 * �Ƿ��Ǹ��ڵ�
	 */
	public boolean isRoot()
	{
		return parent == null;
	}
	
	/*
	 * ���ڵ��Ƿ�չ��
	 */
	public boolean isParentExpand()
	{
		if (parent == null)
			return false;
		return parent.isExpand();
	}
	
	/*
	 * �Ƿ���Ҷ�ڵ�
	 */
	public boolean isLeaf()
	{
		return child.size() == 0;
	}
	
	/*
	 * �õ���ǰ�ڵ�Ĳ㼶
	 */
	public int getLevel() {
		return parent == null?0:parent.getLevel() + 1;
	}
	
	public boolean isExpand() {
		return isExpand;
	}
	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
		if (!this.isExpand)
		{
			//�ݹ��ӽڵ�
			for (Node node : child) {
				node.setExpand(false);
			}
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public List<Node> getChild() {
		return child;
	}
	public void setChild(List<Node> child) {
		this.child = child;
	}
	
	
}

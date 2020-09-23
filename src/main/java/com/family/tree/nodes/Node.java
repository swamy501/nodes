package com.family.tree.nodes;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * @author Swamy
 *
 */
public class Node {
	
	private String nodeName;
	private int nodeId;
	private int parentId;
	private List<Node> childNodes = new ArrayList<>();
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<Node> getChildNodes() {
		return childNodes;
	}
	
	public void setChildNode(Node node) {
		this.childNodes.add(node);
	}
	
	public boolean hasChilds() {
		return this.childNodes.size()>0;
	}
	
	public String toString() {
		return "Node Name="+this.nodeName+ " Node ID="+this.nodeId+" Parent Node ID="+this.parentId;
	}
	
}
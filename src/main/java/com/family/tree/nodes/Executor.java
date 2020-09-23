package com.family.tree.nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 
 * @author Swamy
 *
 */
public class Executor {
	Node rootNode;
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private Map<Integer, Node> constructNodes(String input){
		
		Map<Integer, Node> nodesMap = new HashMap<>();
		// split string of pipe & get node data
		List<String> nodeStrings = Stream.of(input.split(PIPE)).collect(Collectors.toList());
		nodeStrings.stream().forEach( s -> {
			Node node = new Node();
			String[] nodeData = s.split(COMMA);
			node.setParentId(Integer.parseInt(nodeData[0]));
			node.setNodeId(Integer.parseInt(nodeData[1]));
			node.setNodeName(nodeData[2]);
			if(nodeData[0] == null)
				rootNode = node;
			nodesMap.put(Integer.parseInt(nodeData[1]), node);
		});
		
		return nodesMap;
	}
	
	// All nodes rearranged with childNodes
	private Node setChildNodes(Map<Integer, Node> nodesMap) {
		
		nodesMap.entrySet().stream().forEach(nodeid ->
		{
			Node parentNode = nodesMap.get(nodeid.getValue().getParentId());
			parentNode.setChildNode(nodeid.getValue());
		});
		
		
		return rootNode;
	}
	
	private void displayFamilyTree(Node node) {
		
		System.out.println(node);
		node.getChildNodes().forEach(n-> this.displayFamilyTree(n));
		
	}
	public static void main(String[] args) {
		
	}

	private static String PIPE = "|";
	private static String COMMA = ",";
}

package com.family.tree.nodes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
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
//			System.out.println(s);
			Node node = new Node();
			String[] nodeData = s.split(COMMA);
			
			if(!nodeData[0].equalsIgnoreCase("null"))
			node.setParentId(Integer.parseInt(nodeData[0]));
			else
			node.setParentId(-1);
			
			node.setNodeId(Integer.parseInt(nodeData[1]));
			node.setNodeName(nodeData[2]);
			if(nodeData[0].equalsIgnoreCase("null"))
				rootNode = node;
			nodesMap.put(Integer.parseInt(nodeData[1]), node);
		});
		
		return nodesMap;
	}
	
	// All nodes rearranged with childNodes
	private Node setChildNodes(Map<Integer, Node> nodesMap) {
		//System.out.println(nodesMap);
		nodesMap.entrySet().stream().forEach(nodeid ->
		{
//			System.out.println(nodeid.getValue().getParentId());
			if(nodeid.getValue().getParentId() > -1) {
				Node parentNode = nodesMap.get(nodeid.getValue().getParentId());
				parentNode.setChildNode(nodeid.getValue());
			}
		});
		
		
		return rootNode;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private JSONObject displayFamilyTree(Node node) {
		JSONObject root = new JSONObject();
//		System.out.println(node);
		root.put("name", node.getNodeName());
		if(node.hasChilds()) {
		 addChilds(root,node.getChildNodes());
		}
		
		return root;
	}
	
	/**
	 * 
	 * @param root
	 * @param childNodes
	 */
	private void addChilds(JSONObject root, List<Node> childNodes) {
		JSONArray childArray = new JSONArray();
		childNodes.stream().forEach(n -> {
			JSONObject childObject = new JSONObject();
			childObject.put("name", n.getNodeName());
			 if(n.hasChilds()) {
				 addChilds(childObject, n.getChildNodes());
			 }
			childArray.add(childObject);
		});
		
		root.put("childs", childArray );
	}

	public static void main(String[] args) {
		
		String input = "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|1,4,grandkid|2,5,grandkid|5,6,greatgrandkid";
		Executor executor = new Executor();
		executor.setChildNodes(executor.constructNodes(input));
		
		System.out.println(executor.displayFamilyTree(executor.rootNode));
	}

	private static String PIPE = "\\|";
	private static String COMMA = ",";
}
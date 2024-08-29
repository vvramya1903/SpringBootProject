package com.project.figma.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.figma.entity.Node;
import com.project.figma.repository.NodeRepository;

@Service
public class NodeService {
	
	@Autowired
	private NodeRepository repository;
	
	public List<Node> getAllNodes() {
	    return repository.findAll();
	}

	public void saveNodeData(Node node) {
        if (node == null) {
            throw new NullPointerException("Node object is null");
        }

        if (node.getNodeId() == null || node.getNodeId().trim().isEmpty()) {
            throw new IllegalArgumentException("NodeId is required");
        }

        if (node.getNodeName() == null || node.getNodeName().trim().isEmpty()) {
            throw new IllegalArgumentException("NodeName is required");
        }

        if (node.getDescription() != null && !isValidString(node.getDescription())) {
            throw new IllegalArgumentException("Description contains special characters");
        }

        if (node.getMemo() != null && !isValidString(node.getMemo())) {
            throw new IllegalArgumentException("Memo contains special characters");
        }

        if (node.getNodeType() != null && !isValidString(node.getNodeType())) {
            throw new IllegalArgumentException("NodeType contains special characters");
        }

        if (node.getParentNodeGroupName() != null && !isValidString(node.getParentNodeGroupName())) {
            throw new IllegalArgumentException("ParentNodeGroupName contains special characters");
        }

        if (node.getParentNodeGroupId() != null && !isValidString(node.getParentNodeGroupId())) {
            throw new IllegalArgumentException("ParentNodeGroupId contains special characters");
        }

        if (node.getParentNodeName() != null && !isValidString(node.getParentNodeName())) {
            throw new IllegalArgumentException("ParentNodeName contains special characters");
        }

        repository.save(node);
    }
	private boolean isValidString(String str) {
        String specialChars = "!@#$%^&*()";
        for (char c : str.toCharArray()) {
            if (specialChars.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }
	
	String line = "";
	public void saveNodeData() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Nodes.csv"));
			while((line = br.readLine())!=null) {
				String [] data = line.split(",");
				Node node = new Node();
				node.setNodeId(data[0]);
				node.setNodeName(data[1]);
				node.setDescription(data[2]);
				node.setMemo(data[3]);
				node.setNodeType(data[4]);
				node.setParentNodeGroupName(data[5]);
				node.setParentNodeGroupId(data[6]);
				node.setParentNodeName(data[7]);
				repository.save(node);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

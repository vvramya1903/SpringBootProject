package com.project.figma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.figma.entity.Node;
import com.project.figma.service.NodeService;

@RestController
public class NodeController {

	@Autowired
	private NodeService service;
	
	@RequestMapping(path = "feedData")
	public void setDataInDB() {
		service.saveNodeData();
	}
	
    @PostMapping("/nodes")
    public ResponseEntity<String> addNode(@RequestBody Node node) {
        if (node == null) {
            return new ResponseEntity<>("Node object is null", HttpStatus.BAD_REQUEST);
        }

        if (node.getNodeId() == null || node.getNodeId().trim().isEmpty()) {
            return new ResponseEntity<>("NodeId is required", HttpStatus.BAD_REQUEST);
        }

        if (node.getNodeName() == null || node.getNodeName().trim().isEmpty()) {
            return new ResponseEntity<>("NodeName is required", HttpStatus.BAD_REQUEST);
        }

        if (node.getDescription() != null && !isValidString(node.getDescription())) {
            return new ResponseEntity<>("Description contains special characters", HttpStatus.BAD_REQUEST);
        }

        if (node.getMemo() != null && !isValidString(node.getMemo())) {
            return new ResponseEntity<>("Memo contains special characters", HttpStatus.BAD_REQUEST);
        }

        if (node.getNodeType() != null && !isValidString(node.getNodeType())) {
            return new ResponseEntity<>("NodeType contains special characters", HttpStatus.BAD_REQUEST);
        }

        if (node.getParentNodeGroupName() != null && !isValidString(node.getParentNodeGroupName())) {
            return new ResponseEntity<>("ParentNodeGroupName contains special characters", HttpStatus.BAD_REQUEST);
        }

        if (node.getParentNodeGroupId() != null && !isValidString(node.getParentNodeGroupId())) {
            return new ResponseEntity<>("ParentNodeGroupId contains special characters", HttpStatus.BAD_REQUEST);
        }

        if (node.getParentNodeName() != null && !isValidString(node.getParentNodeName())) {
            return new ResponseEntity<>("ParentNodeName contains special characters", HttpStatus.BAD_REQUEST);
        }

        service.saveNodeData(node);
        return new ResponseEntity<>("Node added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/nodes")
    public ResponseEntity<List<Node>> getAllNodes() {
        List<Node> nodes = service.getAllNodes();
        return new ResponseEntity<>(nodes, HttpStatus.OK);
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
	
	
}

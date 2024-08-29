package com.project.figma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.figma.entity.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, String> {

}

package org.base.utils.beannote;

import java.util.Collections;
import java.util.List;

/**
 * 实体节点
 * Created by wangwr on 2016.3.24.
 */
public class Node {

    /**
     * 嵌套层数
     */
    private int index;

    /**
     * 父亲节点
     */
    private Node parent;

    /**
     * 节点key
     */
    private String nodeKey;

    /**
     * 节点value
     */
    private String nodeValue;

    /**
     * 节点描述
     */
    private String nodeNote;

    /**
     * 子节点
     */
    private List<Node> subNodes;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getNodeNote() {
        return nodeNote;
    }

    public void setNodeNote(String nodeNote) {
        this.nodeNote = nodeNote;
    }

    public List<Node> getSubNodes() {
        if(subNodes==null)subNodes= Collections.emptyList();
        return subNodes;
    }

    public void setSubNodes(List<Node> subNodes) {
        this.subNodes = subNodes;
    }
}

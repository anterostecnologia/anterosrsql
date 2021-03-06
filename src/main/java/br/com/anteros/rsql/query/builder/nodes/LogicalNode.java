package br.com.anteros.rsql.query.builder.nodes;

import java.util.LinkedList;
import java.util.List;

public abstract class LogicalNode extends AbstractNode {

    private List<AbstractNode> children = new LinkedList<>();

    public LogicalNode() {}

    public LogicalNode(LogicalNode parent) {
        super(parent);
    }

    public LogicalNode(LogicalNode parent, List<AbstractNode> children) {
        super(parent);
        setChildren(children);
    }

    public List<AbstractNode> getChildren() {
        return children;
    }

    public void setChildren(List<AbstractNode> children) {
        this.children = children;
        children.forEach(child -> child.setParent(this));
    }


}

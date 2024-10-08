package pt.up.fe.specs.jackdaw.joinpoints.statements;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import pt.up.fe.specs.jackdaw.JackdawQueryEngine;
import pt.up.fe.specs.jackdaw.JoinpointCreator;
import pt.up.fe.specs.jackdaw.ParentMapper;
import pt.up.fe.specs.jackdaw.abstracts.AJackdawWeaverJoinPoint;
import pt.up.fe.specs.jackdaw.abstracts.joinpoints.AForStatement;
import pt.up.fe.specs.jackdaw.abstracts.joinpoints.AJoinPoint;
import pt.up.fe.specs.jackdaw.abstracts.joinpoints.ALoop;
import pt.up.fe.specs.jackdaw.abstracts.joinpoints.AWhileStatement;
import pt.up.fe.specs.jackdaw.enums.LoopKind;
import pt.up.fe.specs.util.exceptions.NotImplementedException;

public class JsLoop extends ALoop {

    private final JsonObject node;

    public JsLoop(JsonObject node) {
        this.node = node;
    }

    @Override
    public JsonObject getNode() {
        return node;
    }

    @Override
    public String getKindImpl() {
        throw new NotImplementedException(this);
    }

    @Override
    public Boolean getIsInnermostImpl() {
        List<AJoinPoint> loops = new ArrayList<AJoinPoint>();
        loops.addAll(JackdawQueryEngine.queryNodeGeneric(this.node, AForStatement.class, true));
        loops.addAll(JackdawQueryEngine.queryNodeGeneric(this.node, AWhileStatement.class, true));
        if (loops.size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public Integer getNestedLevelImpl() {
        int nestedLevel = 0;
        boolean reachedMaxParent = false;
        JsonObject currentNode = this.node;
        while (!reachedMaxParent) {
            JsonObject parent = ParentMapper.getParent(currentNode);
            AJackdawWeaverJoinPoint parentJp = JoinpointCreator.create(parent);
            switch (parentJp.getTypeImpl()) {
            case "FunctionDeclaration":
                reachedMaxParent = true;
                break;
            case "Program":
                reachedMaxParent = true;
                break;
            case "WhileStatement":
                nestedLevel++;
                break;
            case "ForStatement":
                nestedLevel++;
                break;
            default:
                break;
            }
            currentNode = parent;

        }

        return nestedLevel;
    }

}

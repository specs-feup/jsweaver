package pt.up.fe.specs.jackdaw.joinpoints;

import com.google.gson.JsonObject;

import pt.up.fe.specs.jackdaw.JackdawRefactor;
import pt.up.fe.specs.jackdaw.JoinpointCreator;
import pt.up.fe.specs.jackdaw.abstracts.joinpoints.ADeclarator;
import pt.up.fe.specs.jackdaw.abstracts.joinpoints.AJoinPoint;

public class JsDeclarator extends ADeclarator {
    private final JsonObject node;

    public JsDeclarator(JsonObject node) {
        this.node = node;
    }

    @Override
    public JsonObject getNode() {
        return this.node;
    }

    @Override
    public AJoinPoint getIdImpl() {
        return JoinpointCreator.create(node.get("id").getAsJsonObject());
    }

    @Override
    public AJoinPoint getInitImpl() {
        JsonObject initObject = this.node.get("init").getAsJsonObject();
        return JoinpointCreator.create(initObject);
    }
    @Override
    public void refactorImpl(String name) {
    	JackdawRefactor.refactorJoinpoint(this,name);
    }

}

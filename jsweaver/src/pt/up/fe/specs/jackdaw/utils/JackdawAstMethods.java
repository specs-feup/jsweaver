/**
 *  Copyright 2020 SPeCS.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package pt.up.fe.specs.jackdaw.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.lara.interpreter.weaver.ast.AAstMethods;
import org.lara.interpreter.weaver.interf.JoinPoint;
import org.lara.interpreter.weaver.interf.WeaverEngine;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import pt.up.fe.specs.jackdaw.JackdawQueryEngine;
import pt.up.fe.specs.jackdaw.JoinpointCreator;
import pt.up.fe.specs.jackdaw.ParentMapper;

public class JackdawAstMethods extends AAstMethods<JsonObject> {

	public JackdawAstMethods(WeaverEngine weaverEngine) {
		super(weaverEngine);
	}

	@Override
	public Class<JsonObject> getNodeClass() {
		return JsonObject.class;
	}

	@Override
	protected JoinPoint toJavaJoinPointImpl(JsonObject node) {
		return JoinpointCreator.create(node);
	}

	@Override
	protected String getJoinPointNameImpl(JsonObject node) {
		return JackdawCommonLanguage.getJoinPointName(node);
	}

	@Override
	protected Object[] getChildrenImpl(JsonObject node) {
		if (node.get("type").getAsString().equals("IfStatement")) {
			var children = new ArrayList<JsonObject>();

			// var testJp = new JsonObject();
			// testJp.add("test",node.get("test"));
			children.add(node.get("test").getAsJsonObject());

			if (!node.get("consequent").isJsonNull()) {
				var thenJp = new JsonObject();
				thenJp.addProperty("type", "ThenStatement");
				thenJp.add("body", node.get("consequent"));
				thenJp.add("loc", node.get("consequent").getAsJsonObject().get("loc"));
				children.add(thenJp);
			}

			if (!node.get("alternate").isJsonNull()) {
				var elseJp = new JsonObject();
				elseJp.addProperty("type", "ElseStatement");
				elseJp.add("body", node.get("alternate"));
				elseJp.add("loc", node.get("alternate").getAsJsonObject().get("loc"));
				children.add(elseJp);
			}
			// System.out.println("Here");
			// System.out.println(children);
			return children.toArray();

		}

		return Arrays.stream(JackdawQueryEngine.getChildren(node)).map(jp -> jp.getNode()).toArray();
	}

	@Override
	protected Object[] getScopeChildrenImpl(JsonObject node) {
		// TODO Auto-generated method stub
		return new Object[0];
	}

	@Override
	protected Object getParentImpl(JsonObject node) {
		if (node.get("type").getAsString().equals("ThenStatement")
				|| node.get("type").getAsString().equals("ElseStatement")) {
			return ParentMapper.getParent(node.get("body").getAsJsonObject());
			
		}
		var parent = ParentMapper.getParent(node);
		if (parent.get("type").getAsString().equals("IfStatement")) {
			if(parent.get("consequent").equals(node)) {
				var thenJp = new JsonObject();
				thenJp.addProperty("type", "ThenStatement");
				thenJp.add("body", parent.get("consequent"));
				thenJp.add("loc", parent.get("consequent").getAsJsonObject().get("loc"));
				return thenJp;
			}
			
			if(parent.get("alternate").equals(node)) {
				var elseJp = new JsonObject();
				elseJp.addProperty("type", "ElseStatement");
				elseJp.add("body", parent.get("alternate"));
				elseJp.add("loc", parent.get("alternate").getAsJsonObject().get("loc"));
				return elseJp;
			}
			
		}

		return parent;
	}

}

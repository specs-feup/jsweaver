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

import java.util.Arrays;

import org.lara.interpreter.weaver.ast.AAstMethods;
import org.lara.interpreter.weaver.interf.JoinPoint;
import org.lara.interpreter.weaver.interf.WeaverEngine;

import com.google.gson.JsonObject;

import pt.up.fe.specs.jackdaw.JackdawQueryEngine;
import pt.up.fe.specs.jackdaw.JoinpointCreator;

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
		return Arrays.stream(JackdawQueryEngine.getChildren(node)).map(jp -> jp.getNode()).toArray();
	}

	@Override
	protected Object[] getScopeChildrenImpl(JsonObject node) {
		// TODO Auto-generated method stub
		return new Object[0];
	}

}

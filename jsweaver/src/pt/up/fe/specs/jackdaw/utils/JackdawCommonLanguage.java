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

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

public class JackdawCommonLanguage {

	private static final Map<String, String> JOINPOINT_MAPPER;
	static {
		JOINPOINT_MAPPER = new HashMap<>();
		JOINPOINT_MAPPER.put("CallExpression", "CallJp");
		JOINPOINT_MAPPER.put("ExpressionStatement", "ExprJp");
		JOINPOINT_MAPPER.put("FunctionDeclaration", "FunctionJp");
		JOINPOINT_MAPPER.put("VariableDeclaration", "DeclJp");
        JOINPOINT_MAPPER.put("ClassDeclaration", "ClassJp");
		JOINPOINT_MAPPER.put("Program", "FileJp");
		JOINPOINT_MAPPER.put("Project", "ProgramJp");
		// JOINPOINT_MAPPER.put("Joinpoint", "JoinPoint");
	}

	public static String getJoinPointName(JsonObject node) {
		var type = node.get("type").getAsString();
		var mapper = JOINPOINT_MAPPER.get(type);
		if (mapper == null)
			return "JoinPoint";
		return JOINPOINT_MAPPER.get(type);
	}

}

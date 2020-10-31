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
import java.util.function.Function;

import com.google.gson.JsonObject;

import pt.up.fe.specs.jackdaw.ParentMapper;

public class JackdawCommonLanguage {

	private static final Map<String, Function<JsonObject, String>> JOINPOINT_MAPPER;
	static {
		JOINPOINT_MAPPER = new HashMap<>();		
		//JOINPOINT_MAPPER.put("WhileStatement", node -> "LoopJp");
		//JOINPOINT_MAPPER.put("ForOfStatement", node -> "LoopJp");
		//JOINPOINT_MAPPER.put("ForInStatement", node -> "LoopJp");
		//JOINPOINT_MAPPER.put("ForStatement", node -> "LoopJp");
		//JOINPOINT_MAPPER.put("DoWhileStatement", node -> "LoopJp");		
		JOINPOINT_MAPPER.put("SwitchCase", node -> "CaseJp");
		JOINPOINT_MAPPER.put("SwitchStatement", node -> "SwitchJp");
		JOINPOINT_MAPPER.put("IfStatement", node -> "IfJp");
		JOINPOINT_MAPPER.put("NewExpression", node -> "ConstructorCallJp");		
		JOINPOINT_MAPPER.put("MethodDefinition", JackdawCommonLanguage::methodDefinition);		
		JOINPOINT_MAPPER.put("Identifier", JackdawCommonLanguage::identifier);
		JOINPOINT_MAPPER.put("VariableDeclaration", node -> "VarDeclJp");
		JOINPOINT_MAPPER.put("MemberExpression", JackdawCommonLanguage::memberExpression);
		JOINPOINT_MAPPER.put("CallExpression", node -> "CallJp");
		JOINPOINT_MAPPER.put("ExpressionStatement", node -> "ExprJp");
		JOINPOINT_MAPPER.put("FunctionDeclaration", node -> "FunctionJp");
		JOINPOINT_MAPPER.put("ClassDeclaration", node -> "ClassJp");
		JOINPOINT_MAPPER.put("Program", node -> "FileJp");
		JOINPOINT_MAPPER.put("Project", node -> "ProgramJp");

		// Not Supported
		// - Field (?)
		// - Type
		// - Decl
		// - Stmt

		// TODO: Param can also be a AssignmentPattern | BindingPattern;
		// TODO: ExpressionStatement is a expr or stmt?

	}

	public static String getJoinPointName(JsonObject node) {
		var type = node.get("type").getAsString();
		var mapper = JOINPOINT_MAPPER.get(type);
		if (mapper == null)
			return "JoinPoint";
		return mapper.apply(node);
	}

	@SuppressWarnings("unused")
	private static String identifier(JsonObject node) {

		var parent = ParentMapper.getParent(node);

		if (parent.has("params") && parent.getAsJsonArray("params").contains(node))
			return "ParamJp";

		// TODO: Confirm this works as intended
		// Does not work when parent is an "If"
		if (!parent.get("type").getAsString().equals("MemberExpression")
				&& parent.get("type").getAsString().contains("Expr"))
			return "VarRefJp";

		return "JoinPoint";
	}

	private static String memberExpression(JsonObject node) {

		var parent = ParentMapper.getParent(node);

		if (parent.get("type").getAsString().equals("CallExpression") 
				&& parent.get("callee").equals(node))
			return "MemberCallJp";

		return "FieldRefJp";
	}
	
	private static String methodDefinition(JsonObject node) {
		
		if(node.get("kind").getAsString().equals("method"))
			return "MethodJp";
		else
			return "ConstructorJp";
	}

//	@SuppressWarnings("unused")
//	private static String callExpression(JsonObject node) {
//		if (false)
//			return "MemberCallJp";
//
//		return "CallJp";
//	}

}

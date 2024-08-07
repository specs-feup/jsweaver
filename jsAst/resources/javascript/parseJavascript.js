var ast = esprima.parseScript(code,{loc:true,comment:true});
/*
// DEBUG
var body = ast["body"];
console.log(body);
for(var value of body) {
	console.log("value: " + value + "\n");
	for(var key in value) {
		console.log(key + ": " + value[key] + "\n");
	}
}
*/
var string = JSON.stringify(ast);
string;
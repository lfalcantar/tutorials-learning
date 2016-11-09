// Neo4j endpoint + user credentials
var neo4jEndpoint = "http://localhost:7474/db/data/transaction/commit";
var neo4jUser = "neo4j";
var neo4jPass = "Neo4j";
var query = "MATCH (n)-[r]->(m) WHERE NOT (n:_META_) AND NOT (m:_META_) RETURN n,r,m";

// Set authorization for AJAX calling
	$.ajaxSetup({
		headers: {
			"Authorization": 'Basic ' + window.btoa(neo4jUser + ":" + neo4jPass)
		}
	});

//getNeo4jData(query);

function getNeo4jData(query){
	var jsonVariable;
	var params = {};
	var queryError = function(data) { jsonVariable = JSON.stringify(data) };
	var querySuccess = function(data) {
		startVisualization(JSON.stringify(data));
	};
	queryNeo4j(query,params,queryError,querySuccess);
}

function addNodeToNeo4j(node){
	var query = "CREATE (n:" + node.label + " { name : '" + node.name + "' }) return n";
	var jsonVariable;
	var params = {};
	var queryError = function(data) { jsonVariable = JSON.stringify(data) };
	var querySuccess = function(data) {
		jsonVariable = JSON.stringify(data);
		addNodeToGraph(jsonVariable)
	};
	queryNeo4j(query,params,queryError,querySuccess);
}

function addEdgeToNeo4j(edge){
	var query = "MATCH (a),(b) WHERE id(a) = " + edge.sourceID +" AND id(b) = " + edge.targetID +" CREATE (a)-[r:" + edge.name + "]->(b) RETURN a,b,r";
	var jsonVariable;
	var params = {};
	var queryError = function(data) { jsonVariable = JSON.stringify(data) };
	var querySuccess = function(data) {
		jsonVariable = JSON.stringify(data);
		addEdgeToGraph(jsonVariable)
	};
	queryNeo4j(query,params,queryError,querySuccess);
}


// Function to query remote Neo4j endpoint, using AJAX POST request
function queryNeo4j(query, params,queryError,querySuccess) {
	$.ajax(neo4jEndpoint, {
		type: "POST",
		data: JSON.stringify({
			statements: [{
				statement: query,
				parameters: params,
				resultDataContents: [/*"row", */"graph"]
			}]
		}),
		dataType: "json",
		contentType: "application/json;charset=UTF-8",
		error: queryError,
		success: querySuccess,
	});
}

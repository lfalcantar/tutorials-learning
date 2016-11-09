var WIDTH = 1650;
var HEIGHT = 700;
var LINKDISTANCE =200;
var nodeCurrentId = 0;
var edgeCurrentId = 0;
var dataset;
var force;
var nodes;
var edges;
var nodelabels;
var edgelabels;
var edgepaths;

function startVisualization(json){
	var json = JSON.parse(json)
	var svg = d3.select("#svgContainer");
	svg.html("");
	dataset = formatData(json);
	fillEdgeForm();

	displayGraph(svg);
}

function displayGraph(svg){
	 var colors = getNodesColors();

	force = d3.layout.force()
        .nodes(dataset.nodes)
        .links(dataset.edges)
        .size([WIDTH,HEIGHT])
        .linkDistance([LINKDISTANCE])
        .charge([-500])
        .theta(0.1)
        .gravity(0.05)
        .start();

    edges = svg.selectAll("line")
      .data(dataset.edges)
      .enter()
      .append("line")
      .attr("id",function(d,i) {return 'edge'+i})
      .attr('marker-end','url(#arrowhead)')
      .style("stroke","#ccc")
      .style("pointer-events", "none");

    nodes = svg.selectAll("circle")
      .data(dataset.nodes)
      .enter()
      .append("circle")
      .attr({"r":30})

      .style("fill",function(d,i){ return colors[d.label] })
      .call(force.drag)
			.on("mouseover", function(d)
			{
				console.log(d.name);
				 d3.select(this).transition()
				 .duration(750)
				 .attr("r", 75)

			})
			.on("mouseout", function(){
				d3.select(this).transition()
				.duration(750)
				.attr("r", 30)
			})
			.on("click", function(d, i){
			});

    nodelabels = svg.selectAll(".nodelabel")
       .data(dataset.nodes)
       .enter()
       .append("text")
       .attr("text-anchor", "middle")
       .attr({"x":function(d){return d.x;},
              "y":function(d){return d.y;},
              "class":"nodelabel",
              "stroke":"black"})
       .text(function(d){return d.name.substring(0,3)+"..."});

    edgepaths = svg.selectAll(".edgepath")
        .data(dataset.edges)
        .enter()
        .append('path')
        .attr({'d': function(d) {return 'M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y},
               'class':'edgepath',
               'fill-opacity':0,
               'stroke-opacity':0,
               'fill':'blue',
               'stroke':'red',
               'id':function(d,i) {return 'edgepath'+i}})
        .style("pointer-events", "none");

    edgelabels = svg.selectAll(".edgelabel")
        .data(dataset.edges)
        .enter()
        .append('text')
        .style("pointer-events", "none")
        .attr({'class':'edgelabel',
               'id':function(d,i){return 'edgelabel'+i},
               'dx':80,
               'dy':0,
               'font-size':10,
               'fill':'#aaa'});

    edgelabels.append('textPath')
        .attr('xlink:href',function(d,i) {return '#edgepath'+i})
        .style("pointer-events", "none")
        .text(function(d,i){return d.name});

    svg.append('defs').append('marker')
        .attr({'id':'arrowhead',
               'viewBox':'-0 -5 10 10',
               'refX':40,
               'refY':0,
               //'markerUnits':'strokeWidth',
               'orient':'auto',
               'markerWidth':10,
               'markerHeight':10,
               'xoverflow':'visible'})
        .append('svg:path')
            .attr('d', 'M 0,-5 L 10 ,0 L 0,5')
            .attr('fill', '#ccc')
            .attr('stroke','#ccc');


    force.on("tick", function(){
		edges.attr({"x1": function(d){return d.source.x;},
                    "y1": function(d){return d.source.y;},
                    "x2": function(d){return d.target.x;},
                    "y2": function(d){return d.target.y;}
        });

        nodes.attr({"cx":function(d){return d.x;},
                    "cy":function(d){return d.y;}
        });

        nodelabels.attr("x", function(d) { return d.x; })
                  .attr("y", function(d) { return d.y; });

        edgepaths.attr('d', function(d) { var path='M '+d.source.x+' '+d.source.y+' L '+ d.target.x +' '+d.target.y;
                                           return path});

        edgelabels.attr('transform',function(d,i){
            if (d.target.x<d.source.x){
                bbox = this.getBBox();
                rx = bbox.x+bbox.width/2;
                ry = bbox.y+bbox.height/2;
                return 'rotate(180 '+rx+' '+ry+')';
                }
            else {
                return 'rotate(0)';
                }
        });
    });
}

function formatData(json){
	var results = json.results;
	var data = results[0].data;
	var length = data.length;
	var nodesList = new Array();
	var edgesList = new Array();
	var nodes, node, edges, edge;
	var name, sNodeIndex, eNodeIndex;

    for(var i=0;i<length;i++){
    	if(data[i].graph){
    		nodes = data[i].graph.nodes;
    		for(var j=0;j<nodes.length;j++){
				name =  nodes[j].properties.name;

				if(!name)
					name = "";

    			node = new Node(nodes[j].id, name, nodes[j].labels[0]);
    			if( !isNodeInArray(nodesList, node) )
    				nodesList.push(node);
    		}
    	}
    }

    for(var i=0;i<length;i++){
    	if(data[i].graph){
    		edges = data[i].graph.relationships;
    		for(var j=0;j<edges.length;j++){
    			sNodeIndex = findNodeIndex(nodesList, edges[j].startNode);
    			eNodeIndex = findNodeIndex(nodesList, edges[j].endNode);

    			if(sNodeIndex != -1 && eNodeIndex != -1){
    				edge = new Edge(edges[j].id, edges[j].type, sNodeIndex, eNodeIndex, edges[j].startNode, edges[j].endNode);
    				edgesList.push(edge);
    			}
    		}
    	}
    }

 	dataset = new Object();
 	dataset.nodes = nodesList;
 	dataset.edges = edgesList;
  return dataset;
}

function getNodesColors(){
	var colors = new Array();
	colors["Field"] = "pink";
    colors["Institution"] = "#00ccff";
    colors["Researcher"] = "orange";
    colors["Resource"] = "green";
    colors["Institution_Central"] = "gray";
    return colors;
}

function addNode(){
	var name = document.getElementById("nodeName").value;
	var label = document.getElementById("nodeLabel").value;
	var node = new Node(0,name,label);
	addNodeToNeo4j(node);
}

function addNodeToGraph(json){
	var json = JSON.parse(json)
	var results = json.results;
	var data = results[0].data;
	var length = data.length;

    if(data[0].graph){
    	var nodes = data[0].graph.nodes;
		var name =  nodes[0].properties.name;

		if(!name)
			name = "";

    	var node = new Node(nodes[0].id, name, nodes[0].labels[0]);
    	dataset.nodes.push(node);

		var svg = d3.select("#svgContainer");
		svg.html("");

		displayGraph(svg);
		fillEdgeForm();
		resetNodeForm();
    }
    else{
    	alert("Error inserting node into Neo4j");
    }
}

function resetNodeForm(){
	document.getElementById("nodeName").value = "";
	//document.getElementById("nodeLabel").value = "";
}

function addEdge(){
	var name = document.getElementById("edgeName").value;
	var source = document.getElementById("edgeSource").value;
	var target = document.getElementById("edgeTarget").value;
	var sNodeIndex = findNodeIndex(dataset.nodes, source);
  	var	eNodeIndex = findNodeIndex(dataset.nodes, target);
  	var edge = new Edge(edgeCurrentId,name,sNodeIndex,eNodeIndex, source, target);

  	if(source == target){
		alert("Edge Source and Target cannot be the same. Please select a different Target or Source");
		return;
	}

	addEdgeToNeo4j(edge);

	/*var svg = d3.select("#svgContainer");

	svg.html("");

	dataset.edges.push(edge);
	force.stop();
	displayGraph(svg);
	resetEdgeForm();*/
}

function addEdgeToGraph(json){
	var json = JSON.parse(json)
	var results = json.results;
	var data = results[0].data;
	var length = data.length;

    if(data[0].graph){
    	var edges = data[0].graph.relationships;
    	var sNodeIndex = findNodeIndex(dataset.nodes, edges[0].startNode);
    	var	eNodeIndex = findNodeIndex(dataset.nodes, edges[0].endNode);

    	if(sNodeIndex != -1 && eNodeIndex != -1){
    		var edge = new Edge(edges[0].id, edges[0].type, sNodeIndex, eNodeIndex, edges[0].startNode, edges[0].endNode);
    		dataset.edges.push(edge);
    	}

		var svg = d3.select("#svgContainer");
		svg.html("");
		force.stop();

		displayGraph(svg);
		resetEdgeForm();
    }
    else{
    	alert("Error inserting edge into Neo4j");
    }

}

function resetEdgeForm(){
	document.getElementById("edgeName").value = "";
	document.getElementById("edgeSource").value = "";
	document.getElementById("edgeTarget").value = "";
}

function fillEdgeForm(){
	var source = document.getElementById("edgeSource");
	var target = document.getElementById("edgeTarget");

	source.innerHTML = "";
	target.innerHTML = "";


	var nodesObjects = dataset.nodes;
	var options = "";

	for(var i=0;i<nodesObjects.length;i++){
		options += "<option value='" + nodesObjects[i].id + "'>" + nodesObjects[i].name + "</option>";
	}

	source.innerHTML = options;
	target.innerHTML = options;
}

function submitQuery(){
	var query = document.getElementById("query").value;
	//var query = "MATCH (n)-[r]->(m) WHERE NOT (n:_META_) AND NOT (m:_META_) RETURN n,r,m";
	getNeo4jData(query);
}

function submitPredefinedQuery(){
	var queryValue = document.getElementById("predefinedQuery").value;
	var query = "";

	switch(queryValue){
		case "0": query = 'MATCH (n:Researcher) -[r:isFromInstitution]->(m:Institution) RETURN n,r,m'; break;
		case "1": query = 'MATCH (n:Researcher) -[r:isFromInstitution]->(m:Institution) WHERE m.name = "University_of_Texas_at_El_Paso" RETURN n,r,m'; break;
		case "2": query = 'MATCH ()-[r:isExpertIn]->() RETURN r;'; break;
		case "3": query = 'MATCH ()-[r:canContributeTo]->() RETURN r'; break;
		default: query = ''; break;
	}

	getNeo4jData(query);
}

function submitExpertInPredefinedQuery(){
	var queryValue = document.getElementById("expertField").value;
	var query = 'MATCH (a)-[r:isExpertIn]->(b) WHERE b.name = "' + queryValue + '" RETURN r';

	getNeo4jData(query);
}

function compare(a,b) {
	if (a.name < b.name)
		return -1;
	if (a.name > b.name)
		return 1;
	return 0;
}

function isNodeInArray(array, node){
	var found = false;
	var length = array.length;
	for(var i=0;i<length;i++){
		if(array[i].id == node.id){
			found = true;
			break;
		}
	}
	return found;
}

function findNodeIndex(nodesList, nodeId){
	var length = nodesList.length;
	var index = -1;
	for(var i=0;i<length;i++){
		if(nodesList[i].id == nodeId){
			index = i;
			break;
		}
	}
	return index;
}

function Node(id, name, label){
	this.id = id;
	this.name = name;
	this.label = label;
}

function Edge(id,name,source,target, sourceID, targetID){
	this.id = id;
	this.name = name;
	this.source = source;
	this.target = target;
	this.sourceID = sourceID;
	this.targetID = targetID;
}

**Problem
The graph visualization inside Neo4j is good to analyze and interact with the Smart Cities database. 
The visualization for the graph is not as 

**Solution
Recreate visualization using D3.js JavaScript library. 

**Neo4j to D3 process

Neo4j provides a REST API that allows querying with Cypher Query Language
Used D3.js is a JavaScript library to create dynamic and interactive data visualizations in the web
An HTML page was created as a place holder for the D3 visualization
Read data from Neo4j using its REST API and AJAX. Data is returned on JSON
Transform JSON in to data for D3
Visualize data using D3

**To use configure the ports in the file neo4j-access.js

**D3 Visualization Functionalities

Query Neo4j data using specified cypher query
Query Neo4j data using predefined queries
Query Neo4j data using predefined queries for a specified field of interest 
Add a new node
Add a new edge

**Future Work
Edit/Delete Node
Edit/Delete Edge
Expand/Collapse Node
Make the code more dynamic to be able to visualization any ontology. 

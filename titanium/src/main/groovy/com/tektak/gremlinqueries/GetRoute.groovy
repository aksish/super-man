package com.tektak.gremlinqueries

import com.thinkaurelius.titan.core.TitanGraph
import com.tinkerpop.blueprints.Graph
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.gremlin.groovy.Gremlin

/**
 * Created by tektak on 6/11/15.
 */
class GetRoute {
    static {
        Gremlin.load();
    }

    public static Map<Vertex, Integer> getPathToHost(TitanGraph graph) {
        println('=======================+++++++++groovy+++++++++=============')
        def all = []
        //g.v(1).out.loop(1){it.object.id != "5" && it.loops < 6}.path
        //graph.V.has('name', 'jack').out.loop(1){it.object.name!='jack' && it.loops< 4}.path.fill(all)
        def startPipe = graph.V.has('name', 'jack').out.loop(1){it.object.name!='miami' && it.loops< 5}.path.filter{it.last().name=='miami'}.transform{it.name}
        for(pipe in startPipe){
            for(v in pipe){
                print(v + ' ')
            }
            println()

        }
        //println(all)
    }

    public static  void mapReduce(TitanGraph graph){
        println('--------------------map-reduce implementation groovy---------------')
    }

    public static Map<Vertex, Integer> getCollabfilter(Graph graph){
        def collab = [];
        println('--------------------Collaborative filtering groovy---------------')
        return graph.V.has('name', 'max').outE('likes').inE('likes').groupCount(collab)

    }
}

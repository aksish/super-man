package com.tektak.graph;

import com.tektak.gremlinqueries.GetRoute;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.attribute.Text;
import com.thinkaurelius.titan.core.schema.TitanManagement;
import com.thinkaurelius.titan.core.util.TitanCleanup;
import com.thinkaurelius.titan.diskstorage.configuration.BasicConfiguration;
import com.thinkaurelius.titan.diskstorage.configuration.Configuration;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.gremlin.Tokens;
import com.tinkerpop.gremlin.java.GremlinPipeline;
import com.tinkerpop.pipes.PipeFunction;
import com.tinkerpop.pipes.branch.LoopPipe;
import com.tinkerpop.pipes.transform.TransformPipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tektak on 6/5/15.
 */
public class TitanMain {

    //load configuration
    public static TitanGraph getGraph(String file){
        TitanGraph graph = TitanFactory.open(file);
        return graph;
    }

    //clean graph schema and indexes
    public static void cleanGraph(boolean clean){
       if(true==clean) {
           TitanGraph graph = getGraph("/home/tektak/IdeaProjects/titanium/conf/titan-hbase-es.properties");
           graph.shutdown();
           TitanCleanup.clear(graph);
       }
        System.exit(1);
    }

    public static void main(final String[] args) {
      /*  cleanGraph(true);*/


        TitanGraph graph = getGraph("/home/tektak/IdeaProjects/titanium/conf/titan-hbase-es.properties");

        /*TitanManagement mgt = graph.getManagementSystem();
        mgt.makeVertexLabel("hello").make();
        mgt.commit();*/

       // graph.addVertexWithLabel("hello").setProperty("testKey", new byte[]{1,0});

        for(Vertex vertex : graph.getVertices()){
            byte[] data = (byte[])vertex.getProperty("testKey");
            for(byte d : data)
                System.out.println("Hello : "+d);
        }
        /*TitanFactory.Builder builder = TitanFactory.build();
        builder.set("storage.backend","hbase");
        builder.set("storage.hostname","localhost");
        builder.set("storage.hbase.skip-schema-check",false);
        graph = builder.open();*/
       /* graph = GraphSchema.createSchema(graph);

        graph = GraphData.create(graph);*/

        /*TitanManagement mgt = graph.getManagementSystem();
        PropertyKey testKey = mgt.makePropertyKey("testKey").dataType(byte[].class).make();
        mgt.commit();
*/
        graph.shutdown();

   /*     Iterable<EdgeLabel> edlabel = mgt.getRelationTypes(EdgeLabel.class);



        TitanTransaction tx = graph.newTransaction();

        System.out.println("----------------------take 1----------------------------");

        Vertex jack = (Vertex)tx.query().has("name","jack").vertices().iterator().next();

        Iterator<Vertex> jackLikes = graph.getVertex(jack).query().labels("drinks").vertices().iterator();
        while (jackLikes.hasNext()){
            System.out.println(":::::::::::::::"+jackLikes.next().getProperty("name"));
        }

        Iterable<Edge> list = tx.query().limit(4).edges();

        Iterable<Vertex> allFemales = tx.query().has("gender", "female").vertices();

        Iterable<Vertex> test = tx.query().has("gender", "female").orderBy("age", Order.DESC).limit(1).vertices();
        for(Vertex user : test){
            System.out.println("Name : "+user.getProperty("name"));
        }
        GremlinPipeline pipe = new GremlinPipeline(graph);
        pipe.V().transform(new PipeFunction() {
            public Object compute(Object o) {
                Vertex v;
                v = (Vertex)o;
                return v.getProperty("name");
            }
        });
        while(pipe.hasNext()){
            System.out.println("================================================......................"+pipe.next(5));
        }


        Iterable<TitanProperty> props = tx.query().properties();
        System.out.println("======> " + props.toString());
        for(TitanProperty property : props){
            System.out.println("======> " + property.toString());
        }
*/

      //  System.out.println("*********************************take 3***********************");
        /*Iterator eter = tx.query().has("gender", Cmp.GREATER_THAN, 10).has("gender", "female").vertices().iterator();
        Iterator maryEter = mary.getVertices(Direction.OUT, "likes").iterator();

        while(maryEter.hasNext()){
            Vertex vm = (Vertex)maryEter.next();
            System.out.println("Mary Likes: " + vm.getProperty("name"));
        }*/

        /*while (eter.hasNext()){
            Vertex vx = (Vertex)eter.next();
            System.out.println("--->: " + vx.getProperty("name") + "| Age: " + vx.getProperty("age") + "| gender: " + vx.getProperty("gender"));
        }*/
       /* GremlinPipeline pip = new GremlinPipeline();
        pip.start(mary).outE("likes").inV().has("gender", "male");
        while(pip.hasNext()){
            Vertex vxx = (Vertex)pip.next();
            System.out.println(vxx.getProperty("name"));
        }
*/


       /* Iterator<Vertex> lists = mary.query().labels("likes").direction(Direction.OUT).has("gender", "male").vertices().iterator();

        System.out.println("Name Node:"+mary.getProperty("name") + " likes");

        while(lists.hasNext()){
            Vertex ed = lists.next();
            //Vertex imx = ed.getVertex(Direction.IN);
            //imx.query().has("gender", "male");
            //System.out.println("name:"+ed.getLabel());
            System.out.println("name: "+ ed.getProperty("name") + " |Gender: " + ed.getProperty("gender"));
        }
*/
         /*   GremlinPipeline pipeline = new GremlinPipeline();
        pipeline.start(jack).as("x").outE("likes").inV().loop("x", new PipeFunction<LoopPipe.LoopBundle, Boolean>() {
            public Boolean compute(LoopPipe.LoopBundle loopBundle) {
                return true;
            }
        }, new PipeFunction<LoopPipe.LoopBundle, Boolean>() {
            public Boolean compute(LoopPipe.LoopBundle loopBundle) {
                *//*Vertex v = (Vertex)loopBundle.getObject();
                if(v.getProperty("gender")!=null){
                    if(v.getProperty("gender").equals("male")){return true;}
                }*//*
                return true;
            }
        }).filter(new PipeFunction<Vertex, Boolean>() {
            public Boolean compute(Vertex vertex) {
                if(vertex.getProperty("gender")!=null){
                    if(vertex.getProperty("gender").equals("male")){return true;}
                }
                return false;
            }
        }).map();

        System.out.println(pipeline.toList());
        System.out.println("*********************************take 3***********************");


        GremlinPipeline pipeline1 = new GremlinPipeline();
        pipeline1.start(graph).V().property("age").order(TransformPipe.Order.DECR).map();
        System.out.println("+++++++++++++++++= >> " + pipeline1.toList());


        //tx.query()




       //tx.query().has("gender", "female").interval("age",10,40).vertices();

        for(Vertex v : test){
            System.out.println(v.getProperty("name") + " : " + v.getProperty("age"));
        }

        Vertex v = (Vertex)tx.query().has("name", "jessie").vertices().iterator().next();
        Iterable<Edge> eses = tx.query().edges();
        for(Edge e1 : eses){
            System.out.println("---------------------------> " +e1.getVertex(Direction.OUT).getProperty("name"));
        }
        Iterable<Edge> edges = v.query().direction(Direction.OUT).edges();


        tx.commit();

        graph.shutdown();


    *//*----------------------------------------------------gremlin queries below-------------------------------------------------------*//*
       *//* Iterable<Vertex> vxes = tx.query().has("name", Text.CONTAINS, "troy").vertices();

        for(Vertex v: vxes){
           // System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + v);
        }


        GremlinPipeline pipe ;
        pipe = new GremlinPipeline(graph);
        //pipe.V().has("name", "troy").outE("lives").property("since");
        //pipe.V().has("name", "troy").outE("lives").inV().inE("lives").property("point");
        pipe.V().has("name", "troy").outE("brother").inV().filter(new PipeFunction<Vertex, Boolean>() {
            public Boolean compute(Vertex o) {
                return (Integer)o.getProperty("age")>10? true : false;
            }
        }).map();

        System.out.println(pipe.toList());

//        System.out.println("=======================Fuzzy searching==========================");
//        for(TitanIndexQuery.Result<Vertex> result: graph.indexQuery("ageGender", "v.gender:(male)").vertices()){
//            System.out.println(result.getElement().getProperty("name") + ": " + result.getScore());
//        }

        *//**//*
        ============================finding shortest path============================
         *//**//*

       // System.out.println(  GetRoute.getPathToHost(graph));
        System.out.println("=======================shortest path==========================");
        final Vertex v1 = graph.getVertices("name", "jack").iterator().next();
        final Vertex v2 = graph.getVertices("name", "jane").iterator().next();
        //System.out.println(v2);

        final GremlinPipeline<String, List> pipe1 = new GremlinPipeline<String, List>(v1)
                .as("x")
                .both()
                .except("x")
                .loop("x", new PipeFunction<LoopPipe.LoopBundle<Vertex>, Boolean>() {

                            public Boolean compute(LoopPipe.LoopBundle<Vertex> bundle) {
                                return bundle.getLoops() < 4 && bundle.getObject() != v2;
                            }
                        }
                )
                .filter(new PipeFunction<Vertex, Boolean>() {
                    public Boolean compute(Vertex vertex) {
                        return vertex.getProperty("name").equals("jane");
                    }
                })
                .path();
        //System.out.println(pipe1.toList());
        System.out.println("--------------------------------------- path here ---------------------------------------------------");
        for(final List path : pipe1){
            Vertex vl;
            for(Object v : path){
                vl = (Vertex)v;
                System.out.print(((Vertex) v).getProperty("name") + " ");
            }
            System.out.println();
                // System.out.println(path);
        }

        //call to groovy queries
        GetRoute.getPathToHost(graph);

        tx.commit();

        graph.shutdown();*//*
*/

    }

}

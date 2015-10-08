package com.tektak.graph;

import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanTransaction;
import com.thinkaurelius.titan.core.attribute.Geoshape;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.util.ElementHelper;

/**
 * Created by tektak on 6/9/15.
 */
public class GraphData {
    public static void main(String[] args) {

    }
    public static TitanGraph create(TitanGraph graph){

        TitanTransaction tx = graph.newTransaction();

        /*--------------------Vertices--------------------------*/
        Vertex jack = tx.addVertexWithLabel("people");
        jack.setProperty("name", "jack");
        jack.setProperty("age", 34);
        jack.setProperty("gender", "male");

        Vertex troy  = tx.addVertexWithLabel("people");
        troy.setProperty("name", "troy");
        troy.setProperty("age", 64);
        troy.setProperty("gender", "male");

        Vertex mary = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(mary, "name", "mary", "age", 27, "gender", "female");

        Vertex max = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(max, "name", "max", "age", 37, "gender", "male");

        Vertex bob = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(bob, "name", "bob", "age", 17, "gender", "male");

        Vertex jessie = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(jessie, "name", "jessie", "age", 23, "gender", "female");

        Vertex jane = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(jane, "name", "jane", "age", 17, "gender", "female");

        Vertex roy = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(roy, "name", "roy", "age", 43, "gender", "male");

        Vertex miriam = tx.addVertexWithLabel("person");
        ElementHelper.setProperties(miriam, "name", "miriam", "age", 17, "gender", "female");

        Vertex kansas = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(kansas, "name", "kansas", "country", "usa");

        Vertex quebec = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(quebec, "name", "quebec", "country","canada");

        Vertex ontario = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(ontario, "name", "ontario", "country","canada");

        Vertex berlin = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(berlin, "name", "berlin", "country","germany");

        Vertex california = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(california, "name", "california", "country","usa");

        Vertex newyork = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(newyork, "name", "newyork", "country","usa");

        Vertex miami = tx.addVertexWithLabel("place");
        ElementHelper.setProperties(miami, "name", "miami", "country", "usa");

        Vertex tea = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(tea, "name", "tea", "type","caffine");

        Vertex coffee = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(coffee, "name", "coffee", "type","caffine");

        Vertex cocacola = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(cocacola, "name", "cocacola", "type","soda");

        Vertex pepsi = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(pepsi, "name", "pepsi", "type","soda");

        Vertex sprite = tx.addVertexWithLabel("drink");

        ElementHelper.setProperties(sprite, "name", "sprite", "type","soda");

        Vertex carlsberg = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(carlsberg, "name", "carlsberg", "type","beer");

        Vertex tuborg = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(tuborg, "name", "tuborg", "type","beer");

        Vertex budweiser = tx.addVertexWithLabel("drink");
        ElementHelper.setProperties(budweiser, "name", "budweiser", "type","beer");

        Vertex mit = tx.addVertexWithLabel("university");
        ElementHelper.setProperties(mit, "name", "mit", "totalStudents", 4000,  "rating",4.2);

        Vertex stanford = tx.addVertexWithLabel("university");
        ElementHelper.setProperties(stanford, "name", "stanford","totalStudents", 7000, "rating",4.7);

        Vertex harvard = tx.addVertexWithLabel("university");
        ElementHelper.setProperties(harvard, "name", "harvard","totalStudents", 4600, "rating",4.4);

        Vertex princeton = tx.addVertexWithLabel("university");
        ElementHelper.setProperties(princeton, "name", "princeton","totalStudents", 3000, "rating",4.1);


      /*--------------------Edges--------------------------*/
        jack.addEdge("likes", mary);
        jack.addEdge("cousin", bob);
        ElementHelper.setProperties(jack.addEdge("study", harvard), "passedYear", 1992, "gpa", 3.7);
        ElementHelper.setProperties(miriam.addEdge("study", princeton), "passedYear", 2008, "gpa", 3.4);
        ElementHelper.setProperties(mary.addEdge("study", stanford), "passedYear", 2004, "gpa", 3.5);
        ElementHelper.setProperties(jessie.addEdge("study", mit), "passedYear", 2004, "gpa", 3.5);
        jack.addEdge("drinks", carlsberg);
        jack.addEdge("drinks", tea);
        bob.addEdge("drinks", budweiser);
        jessie.addEdge("drinks", coffee);
        jessie.addEdge("drinks", tea);
        ElementHelper.setProperties(jack.addEdge("lives", california), "since", 2004);
        mary.addEdge("cousin", troy);
        troy.addEdge("cousin", mary);
        troy.addEdge("classmates", max);
        troy.addEdge("brother", jane);
        ElementHelper.setProperties(troy.addEdge("lives", miami),"since", 2003, "point",Geoshape.point(22.4f,12.34f));
        ElementHelper.setProperties(jessie.addEdge("lives", kansas),"since", 2001, "point",Geoshape.point(22.12f,12.44f));
        ElementHelper.setProperties(jane.addEdge("lives", berlin),"since", 2007, "point",Geoshape.point(22.52f,12.74f));
        ElementHelper.setProperties(miriam.addEdge("lives", california),"since", 2009, "point",Geoshape.point(22.32f,12.40f));
        max.addEdge("friend", jane);
        mary.addEdge("likes", max);
        mary.addEdge("likes", bob);
        mary.addEdge("likes", coffee);
        bob.addEdge("drinks", coffee);
        max.addEdge("classmates", troy);
        jane.addEdge("friend", max);



        tx.commit();

        return  graph;

    }

}

package com.tektak.graph;

import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.attribute.Decimal;
import com.thinkaurelius.titan.core.attribute.Geoshape;
import com.thinkaurelius.titan.core.schema.*;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/**
 * Created by tektak on 6/9/15.
 */
public class GraphSchema {
    public static TitanGraph createSchema(TitanGraph graph){



        TitanManagement mgt = graph.getManagementSystem();

         /*-----------------------------Vertex label---------------------*/
        VertexLabel people =  mgt.makeVertexLabel("people").make();
        VertexLabel drink = mgt.makeVertexLabel("drink").make();
        VertexLabel place = mgt.makeVertexLabel("place").make();
        VertexLabel university = mgt.makeVertexLabel("university").make();


        /*-----------------Edge labels------------------------------------*/
        EdgeLabel friend = mgt.makeEdgeLabel("friend").multiplicity(Multiplicity.MULTI).make();
        EdgeLabel like = mgt.makeEdgeLabel("likes").make();
        EdgeLabel classmates = mgt.makeEdgeLabel("classmates").make();
        EdgeLabel brother = mgt.makeEdgeLabel("brother").make();
        EdgeLabel live = mgt.makeEdgeLabel("live").make();
        EdgeLabel study = mgt.makeEdgeLabel("study").make();
        EdgeLabel visit = mgt.makeEdgeLabel("visit").make();
        EdgeLabel cousin = mgt.makeEdgeLabel("cousin").make();
        EdgeLabel drinks = mgt.makeEdgeLabel("drinks").make();

        /*---------------------------properties----------------------------*/
        final PropertyKey name = mgt.makePropertyKey("name").dataType(String.class).make();
        final PropertyKey age = mgt.makePropertyKey("age").dataType(Integer.class).make();
        final PropertyKey gender = mgt.makePropertyKey("gender").dataType(String.class).make();
        final PropertyKey type = mgt.makePropertyKey("type").dataType(String.class).make();
        final PropertyKey country = mgt.makePropertyKey("country").dataType(String.class).make();
        final PropertyKey point = mgt.makePropertyKey("point").dataType(Geoshape.class).make();
        final PropertyKey since = mgt.makePropertyKey("since").dataType(Integer.class).make();
        final PropertyKey passedYear = mgt.makePropertyKey("year").dataType(Integer.class).make();
        final PropertyKey month = mgt.makePropertyKey("month").dataType(String.class).make();
        final PropertyKey description = mgt.makePropertyKey("descriptin").dataType(String.class).make();
        final PropertyKey cost = mgt.makePropertyKey("cost").dataType(Integer.class).make();
        final PropertyKey totalStudents = mgt.makePropertyKey("totalStudents").dataType(Integer.class).make();
        final PropertyKey rating = mgt.makePropertyKey("rating").dataType(Decimal.class).make();
        final PropertyKey gpa = mgt.makePropertyKey("gpa").dataType(Decimal.class).make();

        /*------------------------indexes---------------------------------*/
        TitanGraphIndex idxName = mgt.buildIndex("ixName", Vertex.class).addKey(name).unique().buildCompositeIndex();
        mgt.setConsistency(idxName, ConsistencyModifier.LOCK);

        mgt.buildIndex("idxAgeGender",Vertex.class).addKey(age).addKey(gender).buildMixedIndex(GraphConstants.INDEX_NAME);

        mgt.buildIndex("ixType", Vertex.class).addKey(type).buildCompositeIndex();

        mgt.buildIndex("ixCountry", Vertex.class).addKey(country).indexOnly(place).buildCompositeIndex();

        mgt.buildIndex("eidxYearMonth", Edge.class).addKey(passedYear).addKey(month).buildMixedIndex(GraphConstants.INDEX_NAME);

        mgt.buildIndex("idxDrinksDesc", Vertex.class).addKey(description, Parameter.of("mapping", Mapping.TEXT)).buildMixedIndex(GraphConstants.INDEX_NAME);

        mgt.buildIndex("idxStudRating", Vertex.class).addKey(totalStudents).addKey(rating).buildMixedIndex(GraphConstants.INDEX_NAME);

        mgt.buildEdgeIndex(live, "eixPointSince", Direction.OUT, since);

        mgt.buildEdgeIndex(visit, "eixYear", Direction.BOTH, passedYear);

        mgt.buildEdgeIndex(visit, "eixMonth", Direction.BOTH.BOTH, month);

        mgt.buildEdgeIndex(visit, "eixCost", Direction.IN, Order.DESC, cost);

        mgt.buildEdgeIndex(study, "eixGpa", Direction.IN, Order.DESC, gpa);


        mgt.commit();

        return  graph;
    }

}

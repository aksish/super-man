package com.tektak.dao;

import org.apache.thrift.meta_data.FieldMetaData;

/**
 * Created by tektak on 7/14/15.
 */
public class PersonDaoIntf {
    public static void main(String[] args) {
        Tweet tweet  = new Tweet();
        tweet.setLanguage("eng");
        tweet.setText("Awesome text");
        System.out.println("language is set: "  + tweet.isSetLanguage());
        System.out.println(tweet.isSet(Tweet._Fields.TEXT));
    }
}

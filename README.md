<center><img src="https://i.imgur.com/NjA0svv.png"></center>

# Band
[![Build Status](https://travis-ci.org/temannin/Band.svg?branch=master)](https://travis-ci.org/temannin/Band) ![Maintenance](https://img.shields.io/maintenance/yes/2019.svg)

Easy to use, blazing fast JSON document store and retrieve with customizable indexing.

Inspired by the popular solutions Apache Lucene and Elasticsearch, I wanted to create a fast, JSON document store that didn't require an external web service to be running so Band was born. 

Band is able to take in a sizeable amount of JSON documents and index them for search later by criteria defined by the user. For example if were loading 10,000 JSON docs into Band that looked like this:

~~~~
{
   "city":"Boulder",
   "state":"Colorado",
   "population":"20,000",
   "founded": 1959
}
//  Stored in jsonDocument variable
~~~~

We would do 

~~~~
Band band = new Band();
band.addToBand(jsonDocumentVar, new String[] {"city", "state"})

band.search("city","Boulder"); // Returns new band with new subset of data
band.search("state","Colorado"); // Returns new band with new subset of data
~~~~




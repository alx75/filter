### What is it ? :
this in an API for querying the set of available resources.
The API provides filtering functionality allowing clients to select resources which match certain criteria.

### How to check if  my resource match a certain criteria ?

Use one of the filter in the package ```  com.forgerock.filter.types ```  and call the match method.<br/>
Example :
``` 
new GreaterFilter("test","alex").matches(MY_RESOURCE)
``` 

### How to combine filters ?

You can use operators filters to combine filter. Those filters implement ```FilterOperator``` . You can combine as many filters as you want<br/>
Example :
``` 
new ANDFilter(new EqualsFilter("lastname","Walt"),new EqualsFilter("firstname","Joe"))
``` 

For the sake of readability you can use the class FilterBuilder to build complex filters <br/>
Example :
``` 
Filter f = new FilterBuilder()
                .equals("toto","Test")
                .AND().lower("toto","test")
                .OR(new FilterBuilder()
                        .equals("toto","Tests")
                        .AND().equals("test","test")
                        .build())
                .OR().isPresent("toto")
                .OR().isTrue("priviledged")
                .OR().isFalse("admin")
                .AND().NOT().equals("toto","Tests")
                .build();
``` 
### How to print a filter 
=> To print a filter just call the ``` toString```  method

### How to generate filters objects from the string :
=> call ``` FilterGenerator.fromString(MY_FILTER_AS_STRING)``` 

The generator uses two annotations in order to work properly.</br>

The ``` FilterMatch```  annotation which specify the pattern of the filter </br>
The ``` Default```  annotation which identify the constructor that must be used in case several are declared.

### How to create custom filters ?
Just implements the ``` Filter```  interface.
Don't forget to add the ``` FilterMatch```  annotation on your class to specify the pattern used by the filter generator.


We must add a filter.properties in your classpath with the line : 
```
filter.packages=the.package.where.your.filters.live
```

### How to perform some logic based on the structure and content of a filter in a typesafe manner ?
=> call the ``` getContent```  method on a filter to get the content of a filter.


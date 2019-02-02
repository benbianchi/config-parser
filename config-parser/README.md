# Setup:

`mvn clean package`


# To Run:

## With File
Run with the `-f` flag and the path of the file afterwards.
```zsh
java -jar target/config-parser-1.0-SNAPSHOT.jar -f src/test/resources/configs/settings-config.json
```

## Std Input
Run this and enter a String!
```zsh
java -jar target/config-parser-1.0-SNAPSHOT.jar
```



### Querying

`.` = className


`#` = identifer


And keep you class first if you want to select by `Class` (Not ClassName)


# Tracerr

Error message collecting utility library.


## Installing

Add this dependency to the `<dependencies>` section of your `pom.xml` file:

```xml
<dependency>
    <groupId>de.cwkr</groupId>
    <artifactId>tracerr</artifactId>
    <version>0.1</version>
</dependency>
```


## Using

```java
ErrorTrace errorTrace = new ErrorTrace();
errorTrace.isNotBlank(str, "str must not be blank");
errorTrace.isNotEqual(str, "foobar", "str must not be equal to '{}'", "foobar");
errorTRace.logErrors(logger);
errorTrace.throwErrors(CustomException::new, "There were {} errors", errorTrace.countErrors())
```


## Contributing

Feel free to fork this repository and submit pull requests :)

You can also submit issues in case of bugs or feature requests.


## Licensing

[The Apache License, Version 2.0](LICENSE)

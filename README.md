# Tracerr [![Build Status](https://travis-ci.org/cwkr/tracerr.svg?branch=master)](https://travis-ci.org/cwkr/tracerr) [![Coverage Status](https://coveralls.io/repos/github/cwkr/tracerr/badge.svg?branch=master)](https://coveralls.io/github/cwkr/tracerr?branch=master) [![Javadocs](https://www.javadoc.io/badge/de.cwkr/tracerr.svg?color=blue)](https://www.javadoc.io/doc/de.cwkr/tracerr)

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

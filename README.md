# Java Utility Library [![Build Status](https://travis-ci.org/cwkr/util-java.svg?branch=develop)](https://travis-ci.org/cwkr/util-java) [![Coverage Status](https://coveralls.io/repos/github/cwkr/util-java/badge.svg?branch=develop)](https://coveralls.io/github/cwkr/util-java?branch=develop) [![Javadocs](https://www.javadoc.io/badge/de.cwkr/cwkr-util.svg?color=blue)](https://www.javadoc.io/doc/de.cwkr/cwkr-util)


## Installing

Add this dependency to the `<dependencies>` section of your `pom.xml` file:

```xml
<dependency>
    <groupId>de.cwkr</groupId>
    <artifactId>cwkr-util</artifactId>
    <version>1.1.0</version>
</dependency>
```


## Using

The class `de.cwkr.util.Errors` is based on Martin Fowler's **Notification Pattern** (https://www.martinfowler.com/articles/replaceThrowWithNotification.html).

```java
Errors errors = new Errors();
errors.isNotBlank(str, "str must not be blank");
errors.isNotEqual(str, "foobar", "str must not be equal to '{}'", "foobar");
errors.logErrors(logger);
errors.throwErrors(CustomException::new, "There were {} errors", errors.countErrors())
```


## Contributing

Feel free to fork this repository and submit pull requests :)

You can also submit issues in case of bugs or feature requests.


## Licensing

[The Apache License, Version 2.0](LICENSE)

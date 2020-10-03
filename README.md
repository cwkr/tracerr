# Validation Util [![Build Status](https://travis-ci.org/cwkr/util-java.svg?branch=master)](https://travis-ci.org/cwkr/util-java) [![Coverage Status](https://coveralls.io/repos/github/cwkr/util-java/badge.svg?branch=master)](https://coveralls.io/github/cwkr/util-java?branch=master) [![Javadocs](https://www.javadoc.io/badge/de.cwkr.validation/validation-util.svg?color=blue)](https://www.javadoc.io/doc/de.cwkr.validation/validation-util)

Validation utility library.


## Installing

Add this dependency to the `<dependencies>` section of your `pom.xml` file:

```xml
<dependency>
    <groupId>de.cwkr.validation</groupId>
    <artifactId>validation-util</artifactId>
    <version>1.0.2</version>
</dependency>
```


## Using

The class `de.cwkr.validation.util.Errors` is based on Martin Fowler's **Notification Pattern** (https://www.martinfowler.com/articles/replaceThrowWithNotification.html).

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

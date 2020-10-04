# Java Utility Library [![Build Status](https://travis-ci.org/cwkr/util-java.svg?branch=develop)](https://travis-ci.org/cwkr/util-java) [![Coverage Status](https://coveralls.io/repos/github/cwkr/util-java/badge.svg?branch=develop)](https://coveralls.io/github/cwkr/util-java?branch=develop) [![Javadocs](https://www.javadoc.io/badge/de.cwkr/cwkr-util.svg?color=blue)](https://www.javadoc.io/doc/de.cwkr/cwkr-util)


## Installing

Add this dependency to the `<dependencies>` section of your `pom.xml` file:

```xml
<dependency>
    <groupId>de.cwkr</groupId>
    <artifactId>cwkr-util</artifactId>
    <version>1.2.0</version>
</dependency>
```


## Using

The class `de.cwkr.util.Errors` is based on Martin Fowler's **Notification Pattern** (https://www.martinfowler.com/articles/replaceThrowWithNotification.html).

```java
import de.cwkr.util.Errors;

class Example {
    void example() {
        Errors errors = new Errors();
        errors.isNotBlank(str, "str must not be blank");
        errors.isNotEqual(str, "foobar", "str must not be equal to '{}'", "foobar");
        errors.logErrors(logger);
        errors.throwErrors(CustomException::new, "There were {} errors", errors.countErrors());
    }
}
```

The class `de.cwkr.util.Lists` provides static utility methods for `java.util.List` instances.

```java
import static de.cwkr.util.Lists.listOf;
import static de.cwkr.util.Lists.unmodifiableListOf;

class Example {
    void example() {
        List<String> mutableList = listOf("foo", "bar");
        List<String> immutableList = unmodifiableListOf("foo", "bar");
    }
}
```

The class `de.cwkr.util.Maps` provides static utility methods for `java.util.Map` instances.

```java
import static de.cwkr.util.Maps.entryOf;
import static de.cwkr.util.Maps.orderedMapOf;
import static de.cwkr.util.Maps.orderedMapOfEntries;
import static de.cwkr.util.Maps.unmodifiableEntryOf;
import static de.cwkr.util.Maps.unmodifiableMapOf;
import static de.cwkr.util.Maps.unmodifiableMapOfEntries;

class Example {
    void example() {
        Map<String, String> stringMap = orderedMapOf(
            "k1", "v1",
            "k2", "v2",
            "k3", "v3",
            "k4", "v4",
            "k5", "v5"
        );

        Map<String, Object> stringMap2 = orderedMapOfEntries(
            entryOf("k1", "v1"),
            entryOf("k2", "v2"),
            entryOf("k3", "v3"),
            entryOf("k4", "v4"),
            entryOf("k5", "v5")
        );

        Map<String, String> immutableStringMap = unmodifiableMapOf(
            "k1", "v1",
            "k2", "v2",
            "k3", "v3",
            "k4", "v4",
            "k5", "v5"
        );

        Map<String, Object> stringMap2 = unmodifiableMapOfEntries(
            unmodifiableEntryOf("k1", "v1"),
            unmodifiableEntryOf("k2", "v2"),
            unmodifiableEntryOf("k3", "v3"),
            unmodifiableEntryOf("k4", "v4"),
            unmodifiableEntryOf("k5", "v5")
        );
    }
}
```

The class `de.cwkr.util.Sets` provides static utility methods for `java.util.Set` instances.

```java
import static de.cwkr.util.Sets.orderedSetOf;
import static de.cwkr.util.Sets.unmodifiableSetOf;

class Example {
    void example() {
        Set<String> mutableSet = orderedSetOf("foo", "bar");
        Set<String> immutableSet = unmodifiableSetOf("foo", "bar");
    }
}
```


## Contributing

Feel free to fork this repository and submit pull requests :)

You can also submit issues in case of bugs or feature requests.


## Licensing

[The Apache License, Version 2.0](LICENSE)

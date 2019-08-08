


# Start H2 as docker images

## Startng a H2 server

*
* ``java -cp h2-1.4.197.jar org.h2.tools.Server -help``
* [H2-Server-Parameter](http://h2database.com/javadoc/org/h2/tools/Server.html)

```sh
java -cp target/docker/de.kaffeeumeins/h2-docker/build/h2database/h2-1.4.197.jar org.h2.tools.Server -help
```

* How to find all tags for a given docker container name, e.g. openjdk (found somewhere on the internet)

```shell
wget -q https://registry.hub.docker.com/v1/repositories/openjdk/tags -O -  | sed -e 's/[][]//g' -e 's/"//g' -e 's/ //g' | tr '}' '\n'  | awk -F: '{print $3}' | grep 8- | grep alpine
```

http://localhost:8001


## References

* [h2database](http://www.h2database.com)
* [docker-maven-plugin](https://dmp.fabric8.io)
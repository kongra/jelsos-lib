.PHONY: clean test jar install antq cloc

clean:
	@mvn clean

test:
	@mvn test

jar:
	@mvn package -DskipTests

install:
	@mvn install -DskipTests

antq:
	@mvn versions:display-dependency-updates

cloc:
	@cloc . --exclude-list-file=cloc.excluded

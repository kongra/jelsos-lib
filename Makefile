.PHONY: clean test jar install antq cloc

clean:
	@gradle clean

test:
	@gradle test

jar:
	@gradle jar

install:
	@gradle publishToMavenLocal

antq:
	@gradle dependencyUpdates

cloc:
	@cloc . --exclude-list-file=cloc.excluded

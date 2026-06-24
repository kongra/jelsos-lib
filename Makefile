.PHONY: clean test jar install antq cloc format eclipse

GRADLE := $(if $(wildcard gradlew),./gradlew,gradle)

clean:
	@$(GRADLE) clean

test:
	@$(GRADLE) test

jar:
	@$(GRADLE) jar

install:
	@$(GRADLE) publishToMavenLocal

antq:
	@$(GRADLE) dependencyUpdates

cloc:
	@cloc . --exclude-list-file=cloc.excluded

format:
	@$(GRADLE) spotlessApply

eclipse:
	@$(GRADLE) eclipse

clean:
	@mvn clean

cloc:
	@cloc . --exclude-list-file=cloc.excluded

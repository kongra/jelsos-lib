clean:
	@mvn clean

cloc:
	@cloc . --exclude-list-file=cloc.excluded

pom2yaml:
	@mvn io.takari.polyglot:polyglot-translate-plugin:translate -Dinput=pom.xml -Doutput=pom.yml

yaml2pom:
	@mvn io.takari.polyglot:polyglot-translate-plugin:translate -Dinput=pom.yml -Doutput=pom.xml

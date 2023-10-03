WILDFLY_DIR=~/Programming/wildfly-preview-29.0.1.Final/

build:
	./gradlew build

copying_to_wildfly_deployments: build
	cp app/build/libs/2lab.war $(WILDFLY_DIR)/standalone/deployments
echo "## Build docker image"
docker build -f Dockerfile-limits-jdk11 -t myjdk11 .
echo ''
echo "## Run docker image (ok) >> docker run --rm -ti --memory=256m -e JAVA_OPTS=-Xms50M -Xmx50M myjdk11"
docker run --rm -ti --memory=256m -e JAVA_OPTS="-Xms50M -Xmx50M" myjdk11
echo ''
echo "## Run docker image (nok) >> docker run --rm -ti --memory=50m -e JAVA_OPTS=-Xms256M -Xmx256M myjdk11"
docker run --rm -ti --memory=50m -e JAVA_OPTS="-Xms256M -Xmx256M" myjdk11
echo ''
echo "## Remove docker image"
docker image rm myjdk11

echo "## Build docker image"
docker build -f Dockerfile-old-jdk8 -t oldjava8 .
echo ''
echo "## Run docker image (not limiting container memory)"
docker run --rm -ti oldjava8
echo ''
echo "## Run docker image (limiting container memory to 1gb)"
docker run --rm -ti --memory=1g oldjava8
echo ''
echo "## Remove docker image"
docker image rm oldjava8

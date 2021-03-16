echo "## Build docker image"
docker build -f Dockerfile-old-jdk8 -t oldjava8 .
echo "## Run docker image"
docker run --rm -ti oldjava8
echo "## Run docker image (limiting memory 1gb)"
docker run --rm -ti --memory=1g oldjava8
echo "## Remove docker image"
docker image rm oldjava8

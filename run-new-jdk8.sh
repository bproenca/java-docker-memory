echo "## Build docker image"
docker build -f Dockerfile-new-jdk8 -t newjava8 .
echo "## Run docker image"
docker run --rm -ti newjava8
echo "## Run docker image (limiting memory 1gb)"
docker run --rm -ti --memory=1g newjava8
echo "## Remove docker image"
docker image rm newjava8

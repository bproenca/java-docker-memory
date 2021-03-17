echo "## Build docker image"
docker build -f Dockerfile-new-jdk8 -t newjava8 .
echo ''
echo "## Run docker image (not limiting container memory)"
docker run --rm -ti newjava8
echo ''
echo "## Run docker image (limiting container memory to 1gb)"
docker run --rm -ti --memory=1g newjava8
echo ''
echo "## Remove docker image"
docker image rm newjava8

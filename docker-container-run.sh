docker run -it --name currency -p 8085:8085 -p 8787:8787 \
  -e EUREKA_HOST="172.17.0.1"\
  -d currency
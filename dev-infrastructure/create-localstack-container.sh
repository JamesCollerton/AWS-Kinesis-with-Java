#!/usr/bin/env bash

docker run \
  --net host \
  --name localstack-kinesis \
  -d \
  -e "SERVICES=${LOCALSTACK_SERVICES:-kinesis}" \
  -e "DEFAULT_REGION=${AWS_REGION:-us-east-1}" \
  -e "HOSTNAME=${LOCALSTACK_HOSTNAME:-localhost}" \
  -e "KINESIS_ERROR_PROBABILITY=${LOCALSTACK_KINESIS_ERROR_PROBABILITY:-0.0}" \
  -e "DATA_DIR=${LOCALSTACK_DATA_DIR:-/tmp/localstack/data}" \
  localstack/localstack

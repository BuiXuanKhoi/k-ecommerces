# Use the official Elasticsearch base image
COPY C://Learning/elasticsearch/elasticsearch-8.9.0/bin /elasticsearch

# Set environment variables
ENV discovery.type=single-node
MAINTAINER Bui Xuan Khoi



# Expose Elasticsearch ports
EXPOSE 9200 9300


CMD ["elasticsearch"]

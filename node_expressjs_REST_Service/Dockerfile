######################
# Multi staging - Build and Testing Stage
######################
FROM node:8.12.0-alpine as builder

# Set environmental variable for Application Root
RUN mkdir -p /node && chmod -R 777 /node && echo ""
RUN mkdir -p /usr/src/app/node && chmod -R 777 /usr/src/app/node

ENV APP_PATH /usr/src/app/node
ENV BASE_PATH /node 

# Download dependencies
COPY package.json $APP_PATH/package.json
WORKDIR $APP_PATH

# install production dependencies
RUN npm config set -g production false && \
  npm install --unsafe-perm

# Source Files
COPY ./ $APP_PATH

# Test and Build the code
WORKDIR $APP_PATH
RUN npm run tests

# Convert absolute paths to relative paths in coverage report
RUN sed -i 's|SF:/usr/src/app/node/|SF:|g' coverage/lcov.info
RUN cp -r $APP_PATH/coverage $BASE_PATH

# Build the code
RUN npm run build

# Copy the js and security certificate files to distribution folder
RUN cp -r build $BASE_PATH
RUN cp -r node_modules $BASE_PATH
RUN cp -r *.js $BASE_PATH
RUN cp test-result.xml $BASE_PATH
RUN cp package.json $BASE_PATH

######################
# Deploy Stage
######################
FROM node:8.12.0-alpine

# Set environmental variable for Application Root
ENV BASE_PATH /node
WORKDIR $BASE_PATH

# Copy output of previous stage
COPY --from=builder $BASE_PATH $BASE_PATH

EXPOSE 8080

ENTRYPOINT ["npm", "start"]


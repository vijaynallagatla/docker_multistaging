####################################################################################################################################
# Multitaging -> Build and Test Stage
####################################################################################################################################
FROM manu682/node_frontend_stable_chrome as builder

# Download the latest Chrome
RUN apt-get update && apt-get --only-upgrade install google-chrome-stable

# Set environmental variable for Application Root
RUN mkdir -p /Angular-App && chmod -R 777 /Angular-App
RUN mkdir -p /usr/src/app/Angular-App && chmod -R 777 /usr/src/app/Angular-App

ENV APP_PATH /usr/src/app/Angular-App
ENV BASE_PATH /Angular-App

# Download dependencies
COPY package.json $APP_PATH/package.json
WORKDIR $APP_PATH
RUN npm config set -g production false && \
npm config set strict-ssl false && npm config set registry http://registry.npmjs.org && \
  npm install --unsafe-perm

# Add angular cli to PATH variable
ENV PATH $APP_PATH/node_modules/@angular/cli/bin/:$PATH

# Copy Source Files
COPY ./ $APP_PATH

# Test and Build the code
WORKDIR $APP_PATH
RUN npm run tests
# Convert absolute paths to relative paths in coverage report
RUN sed -i 's|SF:/usr/src/app/Angular-App/|SF:|g' test-results/reports/coverage/lcov.info

# Build the code
RUN npm run build-prod

RUN cp -a $APP_PATH/* $BASE_PATH

##############################################################################################################
# Deploy Stage
##############################################################################################################
FROM nginx:1.13-alpine

# Set environmental variable for Application Root
ENV APP_PATH /usr/src/app/Angular-App

# Copy configurations
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY nginx.sh /opt/bin/

# Copy output of previous stage
COPY --from=builder $APP_PATH/dist /usr/share/nginx/html

ENTRYPOINT ["sh", "/opt/bin/nginx.sh"]

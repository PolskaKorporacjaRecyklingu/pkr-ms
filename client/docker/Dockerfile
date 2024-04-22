FROM node:18.16.0-alpine as builder

WORKDIR /usr/src/app

COPY package.json package-lock.json ./
RUN npm i -g @angular/cli@17.3.4
RUN npm install --loglevel silly

COPY . .

ARG PROFILE
ENV PROFILE $PROFILE

RUN echo "Environment: ${PROFILE}"
RUN npm run build-${PROFILE}

FROM nginx

COPY --from=builder /usr/src/app/dist/client/browser /usr/share/nginx/html
COPY --from=builder /usr/src/app/docker/nginx.config /etc/nginx/conf.d/default.conf

EXPOSE 80

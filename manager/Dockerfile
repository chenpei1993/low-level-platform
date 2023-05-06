FROM node:18.16.0 as build-stage
ARG NPM_MODE

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --mode="$NPM_MODE"

ENV PORT=8080
# production stage
FROM nginx:stable-alpine as production-stage
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE $PORT
CMD ["nginx", "-g", "daemon off;"]
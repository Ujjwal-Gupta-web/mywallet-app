FROM node:20-alpine AS build
WORKDIR /build
COPY package.json package-lock.json ./
RUN npm ci
COPY . .
ARG REACT_APP_BASE_URL=https://mywallet.inframan.cloud/api
ENV REACT_APP_BASE_URL=$REACT_APP_BASE_URL
ENV CI=false
RUN npm run build

FROM nginx:1.27-alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /build/build /usr/share/nginx/html
EXPOSE 80

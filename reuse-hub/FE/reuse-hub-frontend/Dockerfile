FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm ci --prefer-offline

COPY . .

RUN npm run build

FROM nginx:1.27-alpinex AS runtime

RUN addgroup -S nginxgroup && adduser -S nginxuser -G nginxgroup

RUN rm -rf /usr/share/nginx/html/*

COPY --from=build /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

RUN chown -R nginxuser:nginxgroup /usr/share/nginx/html \
    && chown -R nginxuser:nginxgroup /var/cache/nginx \
    && chown -R nginxuser:nginxgroup /var/log/nginx \
    && touch /var/run/nginx.pid \
    && chown nginxuser:nginxgroup /var/run/nginx.pid

USER nginxuser

EXPOSE 80

CMD ["sh", "-c", "nginx -t && nginx -g 'daemon off;'"]

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
    CMD wget -qO- http://localhost:80/ || exit 1